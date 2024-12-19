
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252" language="java"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Hasil</title>
  </head>
  <body>
    <s:form beanclass="etanah.view.stripes.hasil.Test1ActionBean">
        
      
        <fieldset>
            <legend>Senarai Semakan </legend>
            <br>
            
                <table border="1" width="95%" align="center">
               
              
                    <tr>
                        <th>No                </th>
                        <th>Dokumen              </th>
                        <th>Catatan              </th> 
                         <th>Imbas              </th>
                        <th>ID Dokumen             </th>  
                    </tr>
                    <tr>
                        <td>1    </td>
                        <td>Bukti Penyampai Borang 16D atau 16E    </td>
                        <td>&nbsp;         </td>
                       <td align="center"><stripes:submit name="keluar" value="Imbas"/> </td>
                        <td> &nbsp;        </td>
                       
                    </tr>
                    
                </table>
        </fieldset>  
        <fieldset>
            <legend>Senarai Semakan Tambahan</legend>
            <br>
            
                <table border="1" width="95%" align="center">
               
              
                    <tr>
                        <th>No                </th>
                        <th>Dokumen              </th>
                        <th>Catatan              </th> 
                         <th>Imbas              </th>
                        <th>ID Dokumen             </th>  
                    </tr>
                    <tr>
                        <td>1    </td>
                        <td> &nbsp;   </td>
                        <td>   &nbsp;       </td>
                       <td align="center"><stripes:submit name="keluar" value="Imbas"/> </td>
                        <td>      &nbsp;    </td>
                    </tr>
                </table>
                
        </fieldset>
       
         
       
        <fieldset>
            <table border="0" align="right">
                <tr>
                    <td>
                         <stripes:submit name="keluar" value="Hantar"/>
                        <stripes:submit name="keluar" value="Keluar"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </s:form>
  </body>
