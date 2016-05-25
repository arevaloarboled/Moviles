using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Messages.DB
{
    class BDHelp
    {
        SQLiteConnection ConnectionVar;
        public BDHelp()
        {
            ConnectionVar = new SQLiteConnection("Data Source=Student.db");
            ConnectionVar.Open();
            var Command = ConnectionVar.CreateCommand();
            Command.CommandText = "CREATE TABLE IF NOT EXISTS messages (id_message INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, sender INTEGER NOT NULL, receiver INTEGER NOT NULL, message VARCHAR(50) NOT NULL, date_message VARCHAR(30));";
            Command.ExecuteNonQuery();
        }
        public void Insert(String Sender, String Receiver, String Message, String DateMessage)
        {
            var Command = ConnectionVar.CreateCommand();
            Command.CommandText = "INSERT INTO messages(sender, receiver, message, date_message) VALUES(" +Sender+", "+Receiver+ ", '" + Message + "', '" + DateMessage + "')";
            Command.ExecuteNonQuery();
        }
        public List<OneMessage> QueryMessages(String Sender, String User)
        {
            List<OneMessage> Messages = new List<OneMessage>();
            var Command = ConnectionVar.CreateCommand(); 
            Command.CommandText = "SELECT message, sender, id_message FROM messages WHERE(sender = " +Sender+" AND receiver = "+User+") OR(receiver = "+Sender+" AND sender = "+User+") ORDER BY id_message ASC";
            SQLiteDataReader sdr = Command.ExecuteReader();
            int Index = 0;
            while (sdr.Read())
            {
                Messages.Add(new OneMessage());
                Messages.ElementAt(Index).Sender= sdr.GetInt32(1);
                Messages.ElementAt(Index).Message = sdr.GetString(0);
                Debug.WriteLine(Messages.ElementAt(Index).Sender);
                Debug.WriteLine(Messages.ElementAt(Index).Message);
                Debug.WriteLine(sdr.GetInt32(2));
                Index += 1;
            }
            return Messages;
         }
        public void Close()
        {
            ConnectionVar.Close();
        }
            

    }
}
