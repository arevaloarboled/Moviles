using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Web.Script.Serialization;
namespace Messages
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public class Contacts
    {
        public Rest.Connection rest { get; set; }
        //String result { get; set; }
        public List<Model.Contact> list { get; set; }
        public async void actualizar()
        {
            string result = await rest.Get_Rest("contacts/" + rest.User);
            var json = new JavaScriptSerializer();
            list = (List<Model.Contact>)json.Deserialize<List<Model.Contact>>(result);
        }
        public Contacts() {
            rest = new Rest.Connection();
            actualizar();
        }
    }
    public partial class MainWindow : Window
    {
        public Contacts viewModel;
        public MainWindow()
        {            
            viewModel=new Contacts();
            DataContext = viewModel;
            InitializeComponent();
        }

        private void Select(object sender, MouseButtonEventArgs e)
        {
            Label label = (Label)sender;
            string username = (string)label.Content;
            Model.Contact userId=null;
            for (int i = 0; i < viewModel.list.Count; i++)
            {
                if (viewModel.list[i].userName == username)
                {
                    userId = viewModel.list[i];
                }
            }
            Chat c = new Chat(userId);
            c.Show();
            this.Close();
        }
    }
}
