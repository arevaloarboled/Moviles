using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web.Script.Serialization;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Messages
{
    /// <summary>
    /// Interaction logic for Archivos.xaml
    /// </summary>
    public class shared
    {
        public Model.Contact user { get; set; }
        public List<Model.archivo> sharedFiles { get; set; }
        public Rest.Connection rest;
        public shared(Model.Contact c) {
            rest = new Rest.Connection();
            user = c;
            sharedFiles = new List<Model.archivo>();
            actualizar();
        }
        public async void actualizar()
        {
            string result = await rest.Get_Rest("shared_files/" + user.userId.ToString() + "/" + rest.User);
            var json = new JavaScriptSerializer();
            sharedFiles = (List<Model.archivo>)json.Deserialize<List<Model.archivo>>(result);
        }
    }
    public partial class Archivos : Window
    {
        public Model.Contact user { get; set; }
        public shared viewModel { get; set; }
        public Archivos(Model.Contact i)
        {
            user = i;
            Title = "Archivos compartidos con " + user.userName;
            viewModel = new shared(i);
            InitializeComponent();
            listFiles.ItemsSource = viewModel.sharedFiles;            
        }
        private void MenuItem_Click(object sender, RoutedEventArgs e)
        {
            viewModel.actualizar();
            listFiles.ItemsSource = viewModel.sharedFiles;
        }
        private void Back(object sender, RoutedEventArgs e)
        {
            Chat c = new Chat(user);
            c.Show();
            this.Close();
        }

        private void listFiles_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {            
            int index = listFiles.SelectedIndex;
            viewModel.rest.Download_File(viewModel.sharedFiles[index].id.ToString(), viewModel.sharedFiles[index].name);
        }
    }
}
