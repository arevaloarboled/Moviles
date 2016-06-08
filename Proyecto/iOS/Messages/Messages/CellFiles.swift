//
//  CellChat.swift
//  Messages
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit

class CellFiles: UITableViewCell {
    
    var file:SharedFiles?
    
    @IBOutlet var FileName: UILabel!
    @IBOutlet var Date: UILabel!
    @IBAction func download(sender: AnyObject) {
        Connect.downloadFile(String(file?.id))
    }
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
}