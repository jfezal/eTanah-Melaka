<%--
    Document   : taipSemulaWartaBrgD
    Created on : Mac 1, 2011, 9:45:56 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pengambilan.TaipSemulaWartaBrgDActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
          <legend>Warta Borang D</legend><br />
          <font color="black">Maklumat Tanah</font>
            <div class="content" align="left">
                <table border="0" width="80%">
                    <tr><td align="left"><label>No Lot :</label></td>
                        <td><font style="text-transform: uppercase">${actionBean}</font></td>
                    </tr>
                    
                    <tr><td align="left"><label>Mukim :</label></td>
                        <td><font style="text-transform: uppercase">${actionBean}</font></td>
                    </tr>
                    <tr><td align="left"><label>Daerah :</label></td>
                       <td><font style="text-transform: uppercase">${actionBean}</font></td>
                    </tr>
                    <tr><td align="left"><label>Luas :</label></td>
                        <td><font style="text-transform: uppercase">${actionBean}</font></td>
                    </tr>
                    <tr><td align="left"><label>Tuan Tanah :</label></td>
                        <td><font style="text-transform: uppercase">${actionBean}</font></td>
                    </tr>
                </table><br />
            </div>

           
        <font color="black">Pengambilan</font>
          <div class="content" align="center">
                <table width="80%">
                    <tr><td align="left" width="30%"><label>Luas diambil :</label></td>
                        <td><font style="text-transform: uppercase"><s:text name="noLot" size="20" /></font></td>
                    </tr>
                    <tr><td align="left" width="30%"><label>Sebab Pengambilan :</label></td>
                        <td><font style="text-transform: uppercase"><s:textarea name="mukim" cols="30" rows="3" /></font></td>
                    </tr>
                </table>
          </div>
        </fieldset>
            <p align="center">
             <s:button class="btn" value="Simpan" name="simpan" />
            <s:button class="btn" value="Jana Warta" name="janaWarta" />
            </p>
    </div>
</s:form>
