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
    public var updating=false
    let thread=AsyncGroup()
    @IBOutlet var tableView: UITableView!
    @IBOutlet var Name: UILabel!
    @IBOutlet var text: UITextField!
    
    public func dontwork(){
        updating=false
    }
    
    public func update() {
        updating=true
        thread.background{
            //print("HOLA")
            let seconds = 4.0
            let delay = seconds*Double(NSEC_PER_SEC)
            let dispatchtime = dispatch_time(DISPATCH_TIME_NOW,Int64(delay))
            while (self.updating){
                dispatch_after(dispatchtime, dispatch_get_main_queue(), {
                    self.getMessages(self.contact!.userId)
                })
            }
            //print("CHAO")
        }
    }
    
    override func viewWillDisappear(animated: Bool) {
        /*updating=false
        thread.wait()*/
        print("chao")
    }
 
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
        //update()
        self.getMessages(contact!.userId)
        //self.scrollToLastRow()
        Name.text=contact?.userName
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        //return TaskManager.Count()
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

