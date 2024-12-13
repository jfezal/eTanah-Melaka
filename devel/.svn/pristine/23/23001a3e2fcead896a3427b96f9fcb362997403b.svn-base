/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.intg;

/**
 *
 * @author mansur
 */
public class GenerateSpeksMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String datemasuk = args[0];
        if(datemasuk != null){
            GenerateSPEKSActionBean speks = new GenerateSPEKSActionBean();
            if(speks.GenerateFilesCron(datemasuk).equals("success")){
                System.out.println("(main) Text fail untuk integrasi SPEKS telah berjaya dijana.");
            }else{
                System.out.println("(main) Text fail untuk integrasi SPEKS TIDAK berjaya dijana.");
            }
        }else{
            System.out.println("(main) Parameter untuk Tarikh tidak disediakan.");
        }
    }

}
