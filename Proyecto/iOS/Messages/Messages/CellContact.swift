//
//  CellContact.swift
//  Messages
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit

class CellContact: UITableViewCell {
    
    @IBOutlet var nombre: UILabel!
    
    var contact:Contact?
    
    @IBOutlet var username: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
}