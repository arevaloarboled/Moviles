//
//  ViewController.swift
//  Messages
//
//  Created by Estudiantes on 3/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit
import JSONHelper
class FilesController: UIViewController,UITableViewDataSource {
    private var Files: Array<SharedFiles> = Array<SharedFiles>()
    public var contact:Contact?
    @IBOutlet var tableView: UITableView!
    
    @IBOutlet var Nombresito: UILabel!
    func getFiles(id : Int) -> Void{
        var value: [[String: AnyObject]]!
        FilesGET(id: id).executeTask() {
            if let v = $0.result.value {
                value = v
                for i in value {
                    self.Files.append(SharedFiles(data:i))
                }
            }
            self.viewWillAppear(true)
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        Nombresito.text=contact?.userName
        self.getFiles(contact!.userId)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        //return TaskManager.Count()
        return Files.count
    }
    func tableView(tableView: UITableView,cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell:CellFiles = tableView.dequeueReusableCellWithIdentifier("Custom3") as! CellFiles
        cell.FileName.text=Files[indexPath.row].name
        cell.Date.text=Files[indexPath.row].date
        cell.file=Files[indexPath.row]
        return cell
    }
    override func viewWillAppear(animated: Bool) {
        tableView.reloadData()
    }
    
}

