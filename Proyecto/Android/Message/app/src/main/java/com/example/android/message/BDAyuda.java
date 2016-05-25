package com.example.android.message;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabase.CursorFactory;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.ArrayList;
        import java.util.List;


public class BDAyuda extends SQLiteOpenHelper

{
    // Variables generales
    BDAyuda ayuda;
    SQLiteDatabase db;
    Context ctx;

    public BDAyuda (Context context) {
        super (context,"msg",null,3);
        ctx=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS msg");
        db.execSQL("CREATE TABLE msg (id_msg INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, remitente INTEGER NOT NULL, receptor INTEGER NOT NULL,mensaje VARCHAR(50) NOT NULL, fecha VARCHAR(30));");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS msg");
        onCreate(db);
    }
    // Métodos para manejar la base de datos
    public void abrir ()
    {
        ayuda=new BDAyuda(ctx);
        db=ayuda.getWritableDatabase();
    }
    public void cerrar ()
    {
        db.close();
    }

    public void insertar (String remitente, String to, String mensaje, String date)
    {
        db.execSQL("INSERT INTO msg(remitente, receptor, mensaje, fecha) VALUES ("+remitente+","+to+",'"+mensaje+"','"+date+"')");
    }

    public List <UnMensaje> consultar (String remitente, String usuario){
        List <UnMensaje> mensajes = new ArrayList<UnMensaje>();
        Cursor c = db.rawQuery("SELECT mensaje, remitente, id_msg FROM msg WHERE (remitente=" +remitente+" AND receptor="+usuario+") OR (receptor="+remitente+" AND remitente="+usuario+") ORDER BY id_msg ASC",null);
        int i=0;
        if (c.moveToFirst()) {
            do {
                mensajes.add(new UnMensaje());
                mensajes.get(i).mensaje = c.getString(0);
                mensajes.get(i).from = Integer.parseInt(c.getString(1));
                //mensaje.from = Integer.parseInt(c.getString(1));
                i=i+1;
            } while (c.moveToNext());
        }
        return mensajes;
    }
}