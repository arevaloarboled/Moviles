//
//  Connection.swift
//  Messages
//
//  Created by Estudiantes on 3/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import Foundation
import Alamofire
import SwiftyJSON
import Async
import JSONHelper
import Restofire

class ContactGET: Requestable {
    
    typealias Model = [[String: AnyObject]]
    var path: String = "contacts/"
    init(){
        path+=Connect.User
    }
}

class MessageGET: Requestable {
    
    typealias Model = [[String: AnyObject]]
    var path: String = "messages/"
    init(id : Int){
        path+=String(id)+"/"+String(Connect.User)
    }
}

class FilesGET: Requestable {
    
    typealias Model = [[String: AnyObject]]
    var path: String = "shared_files/"
    
    init(id:Int){
        path+=String(id)+"/"+Connect.User
    }
    
}

public class Connect {
    static let Url="http://localhost:8191/rest/"
    static public var User="1"
    init(){
        
    }
    public static func get(url:String) -> String {
        var result:String!=""
            var tak=Alamofire.request(.GET, Connect.Url+url).validate()
                .responseString{ Response in
                    result=Response.result.value                                
            }
        return result
    }

    public static func postMessage(user:Int,text:String){
        let parameters:[String: AnyObject] = [
            "from": Int(Connect.User)!,
            "to": user,
            "text": text
        ]
        Alamofire.request(.POST, Connect.Url+"messages", parameters: parameters, encoding: .JSON)
    }
    
    public static func downloadFile(idFile : String)
        
    {
        
        let destination = Alamofire.Request.suggestedDownloadDestination(directory: .DocumentDirectory, domain: .UserDomainMask)
        let urlFile=Connect.Url+"files/"+idFile
        
        Alamofire.download(.GET, urlFile, destination: destination)
            
            .progress { bytesRead, totalBytesRead, totalBytesExpectedToRead in
                
                print(totalBytesRead)
         
                // This closure is NOT called on the main queue for performance
                
                // reasons. To update your ui, dispatch to the main queue.
                
                dispatch_async(dispatch_get_main_queue()) {
                    
                    print("Total bytes read on main queue: \(totalBytesRead)")
                    
                }
                
            }
            
            .response { _, _, _, error in
                
                if let error = error {
                    
                    print("Failed with error: \(error)")
                    
                } else {
                    
                    print("Downloaded file successfully")
                    
                }
                
        }
        
    }
}