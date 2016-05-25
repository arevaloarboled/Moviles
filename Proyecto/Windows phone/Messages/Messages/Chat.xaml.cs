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
using System.Windows.Shapes;
using System.Web.Script.Serialization;
using System.Threading;
using System.ComponentModel;
using System.IO;
using Microsoft.Win32;

namespace Messages
{
    /// <summary>
    /// Interaction logic for Chat.xaml
    /// </summary>   
    public class Message : INotifyPropertyChanged
    {
        DB.BDHelp db;
        public Rest.Connection rest { get; set; }
        public Model.Contact userFrom { get; set; }        
        private List<Model.templateMessage> _messagesFrom;

        public List<Model.templateMessage> messagesFrom
        {
            get { return _messagesFrom; }
            set { _messagesFrom = value; NotifyPropertyChanged("messagesFrom");}
        }

        public List<Model.MessageFrom> update=null;                

        public ICommand Selected { get; set; }
        public event PropertyChangedEventHandler PropertyChanged;

        private void NotifyPropertyChanged(String propertyName)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
            }
        }
       
        public async void actualizarDB()
        {
            string result = await rest.Get_Rest("messages/"+userFrom.userId.ToString()+"/"+ rest.User);
            var json = new JavaScriptSerializer();
            update = (List<Model.MessageFrom>)json.Deserialize<List<Model.MessageFrom>>(result);
            if (update.Count>0)
            {
                for (int i = 0; i < update.Count; i++)
                {                                   
                    db.Insert(update[i].from.ToString(), update[i].to.ToString(), update[i].text.ToString(), update[i].date.ToString());                    
                }
            }
        }
        public bool actualizar()
        {
            List<Model.templateMessage>  messagesFromTmp = new List<Model.templateMessage>();
            List<DB.OneMessage> dbMenssages = db.QueryMessages(userFrom.userId.ToString(), rest.User);
            for (int i = 0; i < dbMenssages.Count; i++)
            {
                if (dbMenssages[i].Sender == userFrom.userId)
                {
                    messagesFromTmp.Add(new Model.templateMessage(userFrom.userId, dbMenssages[i].Message));
                }
                else
                {
                    messagesFromTmp.Add(new Model.templateMessage(Int32.Parse(rest.User), dbMenssages[i].Message));
                }
            }
            if (messagesFromTmp.Count > messagesFrom.Count)
            {
                messagesFrom = messagesFromTmp;
                return true;
            }
            return false;
        }
        public Message(Model.Contact n)
        {
            rest = new Rest.Connection();
            db = new DB.BDHelp();
            userFrom = n;
            messagesFrom = new List<Model.templateMessage>();
            actualizarDB();
            actualizar();
            //hilo = new Thread(new ThreadStart(up));            
            //hilo.Start();            
        }
        public async void send(String texto)
        {
            Model.MessageTo message = new Model.MessageTo(Int32.Parse(rest.User), userFrom.userId, texto);
            var json = new JavaScriptSerializer().Serialize(message);
            rest.Post_Rest("messages", json);
            db.Insert(rest.User, userFrom.userId.ToString(), texto,DateTime.Today.ToString("yyyy-MM-dd")+" " +DateTime.Now.ToString("HH:mm:ss"));
            //messagesFrom.Add(new Model.templateMessage(  Int32.Parse(rest.User), texto));
            actualizar();
        }
    }
    public partial class Chat : Window
    {
        Message viewModel;
        Model.Contact contact;
        public Thread hilo;
        Boolean updating = true;
        public void up()
        {
            updating = true;
            while (updating)
            {
                //await Task.Delay(1000);
                Thread.Sleep(1000);
                viewModel.actualizarDB();
                if(viewModel.actualizar())
                {
                    /*ScrollViewer Scr = (ScrollViewer)scroll;
                    Scr.ScrollToEnd();*/
                }                
            }
        }
        public Chat(Model.Contact i)
        {
            hilo = new Thread(new ThreadStart(up));
            contact = i;
            viewModel = new Message(i);
            hilo.Start();
            DataContext = viewModel;            
            InitializeComponent();
            ScrollViewer Scr = (ScrollViewer)scroll;            
            Scr.ScrollToEnd();
            Title = i.userName;
        }

        private void Click(object sender, RoutedEventArgs e)
        {
            TextBox texto = (TextBox) text;
            viewModel.send(texto.Text);
            texto.Text = "";
            ScrollViewer Scr = (ScrollViewer)scroll;
            Scr.ScrollToEnd();            
        }
        private void MenuItem_Click(object sender, RoutedEventArgs e)
        {
            MenuItem item = (MenuItem)sender;
            if (item.Name == "actualizar")
            {
                viewModel.actualizarDB();
                viewModel.actualizar();
                ScrollViewer Scr = (ScrollViewer)scroll;
                Scr.ScrollToEnd();
            }
            if (item.Name == "enviarArchivo")
            {
                OpenFileDialog choofdlog = new OpenFileDialog();
                choofdlog.Filter = "All Files (*.*)|*.*";
                choofdlog.FilterIndex = 1;
                //choofdlog.Multiselect = true;

                if (choofdlog.ShowDialog() == true)//DialogResult.OK)
                {
                    string sFileName = choofdlog.FileName;
                    //string[] arrAllFiles = choofdlog.FileNames; //used when Multiselect = true                               
                    viewModel.rest.Send_File(sFileName, viewModel.rest.User, contact.userId.ToString());
                }
            }
            if (item.Name == "verArchivos")
            {
                updating = false;
                hilo.Join();
                Archivos m = new Archivos(contact);
                m.Show();
                this.Close();
            }

        }
        private void Back(object sender, RoutedEventArgs e)
        {
            updating = false;
            hilo.Join();
            MainWindow m = new MainWindow();
            m.Show();
            this.Close();
        }

        private void textOnKeyDown(object sender, KeyEventArgs e)
        {
            if (e.Key==Key.Return)
            {
                TextBox texto = (TextBox)text;
                viewModel.send(texto.Text);
                texto.Text = "";
                ScrollViewer Scr = (ScrollViewer)scroll;
                Scr.ScrollToEnd();
            }            
        }
    }
}
