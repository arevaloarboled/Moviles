using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Messages.Model
{
    public class templateMessage
    {
        private String _message;
        public String color { get; set; }
        public String alineamiento { get; set; }
        Rest.Connection rest = new Rest.Connection();
        public String message { get; set; }
        public void Add(String text, int from)
        {
            if (from.ToString() != rest.User)
            {
                color="LightBlue";
                alineamiento = "Left";
            }
            else
            {
                color="White";
                alineamiento = "Right";
            }
            message=text;
        }
        public templateMessage(int i=-1,String text="")
        {
            if (i == -1)
            {
                color = "";
                message = "";
                alineamiento = "";
            }
            else
            {
                if (i.ToString() != rest.User)
                {
                    color="LightBlue";
                    alineamiento = "Left";
                }
                else
                {
                    color="White";
                    alineamiento = "Right";
                }
                message=text;                
            }            
        }        
    }
}
