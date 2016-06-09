//
//  ViewController.swift
//  Messages
//
//  Created by Estudiantes on 3/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit
import JSONHelper
import Async
class ChatController: UIViewController,UITableViewDataSource {
    private var Chat: Array<OneMessage> = Array<OneMessage>()
    private let bd:BDhelp!=BDhelp()
    public var contact:Contact?
    @IBOutlet var tableView: UITableView!
    @IBOutlet var Name: UILabel!
    @IBOutlet var text: UITextField!
 
    @IBAction func Actualizo(sender: AnyObject) {
        self.getMessages(self.contact!.userId)
    }
    
    @IBAction func boton(sender: AnyObject) {
        Connect.postMessage(contact!.userId, text: text.text!)
        self.bd.insert(Int(Connect.User)!, Receiver: contact!.userId, Message: text.text!, DateMessage: "")
        text.text=""
        getMessages(contact!.userId)
    }
    func scrollToLastRow() {
        let indexPath = NSIndexPath(forRow: Chat.count-1 , inSection: 0)
        self.tableView.scrollToRowAtIndexPath(indexPath, atScrollPosition: .Bottom, animated: true)
    }
    
    func getMessages(id : Int) -> Void{
        var value: [[String: AnyObject]]!
        MessageGET(id: id).executeTask() {
            if let v = $0.result.value {
                value = v
                for i in value {
                    var message:MessageFrom=MessageFrom(data:i)
                    self.bd.insert(message.from, Receiver: message.to, Message: message.text, DateMessage: message.date)
                    self.scrollToLastRow()
                }
            }
            self.viewWillAppear(true)
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.getMessages(contact!.userId)
        Name.text=contact?.userName
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return Chat.count
    }
    func tableView(tableView: UITableView,cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        if(Chat[indexPath.row].Sender != Connect.User){
            let cell:CellChat2 = tableView.dequeueReusableCellWithIdentifier("Custom22") as! CellChat2
            cell.message=Chat[indexPath.row]
            cell.texto.text=Chat[indexPath.row].Message
            return cell
        }
        else{
            let cell:CellChat = tableView.dequeueReusableCellWithIdentifier("Custom2") as! CellChat
            cell.message=Chat[indexPath.row]
            cell.texto.text=Chat[indexPath.row].Message
            return cell
        }
    }
    override func viewWillAppear(animated: Bool) {
        Chat=Array<OneMessage>()
        Chat=self.bd.queryMessages(String(contact!.userId))
        tableView.reloadData()
    }    
    
}

