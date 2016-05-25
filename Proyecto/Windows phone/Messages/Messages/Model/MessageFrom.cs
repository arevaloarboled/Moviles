using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Messages.Model
{
    public class MessageFrom
    {
        public int id { get; set; }
        public int from { get; set; }
        public int to { get; set; }
        public String text { get; set; }
        public String date { get; set; }
        public MessageFrom()
        {

        }
    }
}
