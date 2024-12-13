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
            <legend>Carian</legend>
                <table border="0" width="100%">
                    <tr>
                        <td width="25%" align="right">Unit</td>
                        <td width="1%" align="center">:</td>
                        <td>
                            <s:select name="">
                                <s:option value=" "> </s:option>
                                <s:option value=" "> </s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td width="25%" align="right">Kategori Permohonan</td>
                        <td width="1%" align="center">:</td>
                        <td>
                            <s:select name="">
                                <s:option value=" "> </s:option>
                                <s:option value=" "> </s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td width="25%" align="right">ID Permohonan</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td width="25%" align="right">ID Perserahan</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td width="25%" align="right">Nama Pemohon</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/></td>
                    </tr>
                     <tr>
                        <td width="25%" align="right">Tarikh Dari</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/></td>
                    </tr>
                     <tr>
                        <td width="25%" align="right">Tarikh Hingga</td>
                        <td width="1%" align="center">:</td>
                        <td><s:text name=""/></td>
                         <td>
                        <s:submit name="keluar" value="Cari"/>
                        <s:submit name="keluar" value="Isi Semula"/>
                    </td>
                    </tr>
                </table>
        </fieldset>
        
      <fieldset>
            <legend>Senarai Permohonan</legend>
            <br>
                <table border="1" width="95%" align="center" datapagesize="2" >
              
                    <tr>
                        <th>No              </th>
                        <th>Unit/Kategori                </th>
                        <th>ID Berkaitan               </th>
                        <th>Tindakan    </th>
                        <th>Tarikh Terima   </th>
                        <th>Keutamaan              </th>
                        <th>Sasaran (Hari)   </th>
                        <th>Bil Hari              </th>
                    </tr>
                    <tr>
                        <td><s:checkbox name="chx1"/></td>
                        <td>Tiada Data          </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                        <td>&nbsp;              </td>
                      
                       
                    </tr>
                </table>
                <br>
        </fieldset>
        
        <fieldset>
            <table border="0" align="right">
                <tr>
                    <td>
                        
                        <s:submit name="keluar" value="Keluar"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </s:form>
  </body>
</html>