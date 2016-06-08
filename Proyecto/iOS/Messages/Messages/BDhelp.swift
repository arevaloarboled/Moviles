//
//  BDhelp.swift
//  Messages
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import Foundation
import SQLite

class BDhelp
{
    private var db:Connection
    private var messages: Table
    private var id_message: Expression<Int>
    private var sender: Expression<Int>
    private var receiver: Expression<Int>
    private var message: Expression<String>
    private var date_message: Expression<String>
    init()
    {
        let path = NSSearchPathForDirectoriesInDomains(.DocumentDirectory, .UserDomainMask, true).first!
        
        db = try! Connection("\(path)/db.sqlite3")
        print("\(path)")
        
        messages = Table("messages")
        id_message = Expression<Int>("id_message")
        sender = Expression<Int>("sender")
        receiver = Expression<Int>("receiver")
        message = Expression<String>("message")
        date_message = Expression<String>("date_message")
        
        try! db.run(messages.create(ifNotExists: true) { t in
            t.column(id_message, primaryKey: PrimaryKey.Autoincrement)
            t.column(sender)
            t.column(receiver)
            t.column(message)
            t.column(date_message)
            })
    }
    func insert(Sender: Int, Receiver: Int, Message: String, DateMessage: String)
    {
        try! db.run(messages.insert(sender <- Sender, receiver <- Receiver,message <- Message, date_message <- DateMessage))
    }
    func queryMessages(Sender: String) -> Array<OneMessage>//[OneMessage]
    {
        let query: String = "SELECT message,sender,id_message FROM messages WHERE (sender = "+Sender+" AND receiver = "+Connect.User+") OR (receiver = "+Sender+" AND sender = "+Connect.User+" ) ORDER BY id_message ASC"
        var messages:Array<OneMessage>=Array<OneMessage>()
        for sms in try! db.prepare(query){
            messages.append(OneMessage(send:String(sms[1]!),message:String(sms[0]!)))
        }
        return messages
    }
}