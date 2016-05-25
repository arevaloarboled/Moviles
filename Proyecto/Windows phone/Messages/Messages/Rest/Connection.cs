using RestSharp;
using RestSharp.Extensions;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace Messages.Rest
{
    public class Connection
    {
        public String User = "2";
        String Url ;
        String UrlClient = "http://192.168.250.83:8191";
        String UserPath = Environment.GetFolderPath(Environment.SpecialFolder.UserProfile);
        String DownloadPath;
        public Connection()
        {
            DownloadPath = Path.Combine(UserPath, "Downloads/");
            Url = UrlClient + "/rest/";
        }

        public async Task<String> Get_Rest(string url)
        {
            url = Url + url;
            //HttpClient client = new HttpClient();
            //Task<string> getStringTask = client.GetStringAsync(Url + url);
            //string urlContents = await getStringTask;
            //return urlContents;
            ////////////////////////
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            try
            {
                WebResponse response = request.GetResponse();
                using (Stream responseStream = response.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(responseStream, Encoding.UTF8);
                    return reader.ReadToEnd();
                }
            }
            catch (WebException ex)
            {
                WebResponse errorResponse = ex.Response;
                using (Stream responseStream = errorResponse.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(responseStream, Encoding.GetEncoding("utf-8"));
                    String errorText = reader.ReadToEnd();
                    // log errorText
                }
                throw;
            }
            return "Error";
        }
        // POST a JSON string
        public async void Post_Rest(string url, string jsonContent)
        {
            url = Url + url;
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.Method = "POST";

            System.Text.UTF8Encoding encoding = new System.Text.UTF8Encoding();
            Byte[] byteArray = encoding.GetBytes(jsonContent);

            request.ContentLength = byteArray.Length;
            request.ContentType = @"application/json";

            using (Stream dataStream = request.GetRequestStream())
            {
                dataStream.Write(byteArray, 0, byteArray.Length);
            }
            long length = 0;
            try
            {
                using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
                {
                    length = response.ContentLength;
                }
            }
            catch (WebException ex)
            {
                // Log exception and throw as for GET example above
            }
        }
        public void Send_File(string fileName, string sender, string receiver)
        {
            var client = new RestClient(UrlClient);
            var request = new RestRequest("rest/files/" + receiver + "/" + sender, Method.POST);
            request.AddFile("file", fileName);
            var result = client.ExecuteAsync(request, (response) =>
            {
                if (response.StatusCode == HttpStatusCode.OK)
                {
                    MessageBox.Show("Archivo enviado correctamente!");
                }
                else
                {
                    MessageBox.Show("Error al enviar el archivo");
                }
            });
        }
        public void Download_File(string fileId, string fileName)
        {
            var client = new RestClient(UrlClient);
            var request = new RestRequest("rest/files/" + fileId, Method.GET);
            client.DownloadData(request).SaveAs(DownloadPath + fileName);
            MessageBox.Show("Descargado correctamente!");
        }
    }
}
