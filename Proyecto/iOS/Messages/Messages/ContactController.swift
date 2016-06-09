//
//  ViewController.swift
//  Messages
//
//  Created by Estudiantes on 3/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit
import JSONHelper

class ContactController: UIViewController,UITableViewDataSource {
    private var Contacts: Array<Contact> = Array<Contact>()
    @IBOutlet var tableView: UITableView!
    
    func getContacts(id : Int) -> Void{
        var value: [[String: AnyObject]]!
        ContactGET().executeTask() {            
            if let v = $0.result.value {
                value = v
                for i in value {
                    self.Contacts.append(Contact(data:i))
                }
            }
            self.viewWillAppear(true)
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.getContacts(Int(Connect.User)!)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return Contacts.count
    }
    func tableView(tableView: UITableView,cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell:CellContact = tableView.dequeueReusableCellWithIdentifier("Custom1") as! CellContact
        cell.contact=Contacts[indexPath.row]
        cell.nombre.text=Contacts[indexPath.row].userName
        cell.username.text=Contacts[indexPath.row].nombre
        return cell
    }
    override func viewWillAppear(animated: Bool) {
        tableView.reloadData()
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        var contact:Contact?
        if (segue.identifier == "Chat"){
            let cell:CellContact?
            cell=sender as! CellContact
            contact=cell?.contact
            let tab = segue.destinationViewController as! tabChatController
            let destino = tab.viewControllers![0] as! ChatController
            let destino2 = tab.viewControllers![1] as! FilesController
            destino.contact=contact
            destino2.contact=contact
        }
        (segue.destinationViewController as! tabChatController).contact = contact!
    }

}

