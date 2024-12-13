<%-- 
    Document   : pertanyaan_cukai_3
    Created on : Dec 14, 2009, 2:39:10 PM
    Author     : nurfaizati
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<style type="text/css">
    #tdLabel {
            color:#003194;
            display:block;
            float:left;
            font-family:Tahoma;
            font-size:13px;
            font-weight:bold;
            margin-left:30px;
            margin-right:0.5em;
            text-align:right;
            width:15em;
        }

   #tdDisplay {
            color:black;
            font-size:13px;
            font-weight:normal;
            float:left;
            width:60em;
        }
</style>

<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikActionBean">

        <s:hidden name="hakmilik.idHakmilik"/>

        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik
                </legend>
                <table border="0" width="100%">
                <tr>
                    <td id="tdLabel">Syarat Nyata :</td>
                    <td id="tdDisplay">${actionBean.hakmilik.syaratNyata.syarat}&nbsp;</td>
                </tr>


                <tr>
                    <td id="tdLabel">Sekatan Kepentingan :</td>
                    <td id="tdDisplay">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</td>
                </tr>
                </table>

                <p>
                    <label>&nbsp;</label>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>

            </fieldset>

        </div>
    </s:form>
</div>



