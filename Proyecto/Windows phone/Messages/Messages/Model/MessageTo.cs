using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Messages.Model
{
    public class MessageTo
    {
        public int from { get; set; }
        public int to { get; set; }
        public String text { get; set; }
        public MessageTo(int from=0, int to = 0, String text = "")
        {
            this.from = from;
            this.to = to;
            this.text = text;
        }        
    }
}
