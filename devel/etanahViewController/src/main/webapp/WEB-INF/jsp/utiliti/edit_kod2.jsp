<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utiliti Kod</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/css/formdesign.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript">

            function tutup(){  
                self.opener.confirmRefresh() ;
                self.close() ;              
            }
           
        </script>
    <body>
        <s:form beanclass="etanah.view.utility.ListKodUtil">
            <s:messages/>
            <s:errors/>
            <div class="subtitle">


                <fieldset class="aras1">
                    <s:hidden name="nameTable" id="nameTable"/>
                    <s:hidden name="${actionBean.columns}" id="money" value="${actionBean.columns}"/>
                    <legend>
                        Edit Kod 
                    </legend>
                    <div align="center">
                        <table class="tablecloth">
                            <tr>
                                <td>Kod :</td>
                                <td><s:text name="kod" id="kod" onblur="this.value=this.value.toUpperCase();" readonly="false"/></td>
                            </tr>
                            <tr>
                                <td>Derah :</td>
                                <td><s:text name="Daerah" id="Daerah" onblur="this.value=this.value.toUpperCase();" readonly="false"/></td>
                            </tr>
                            <tr>
                                <td>Kod BPM:</td>
                                <td><s:text name="KodBPM" id="KodBPM" onblur="this.value=this.value.toUpperCase();" readonly="false"/></td>
                            </tr>
                            <tr>
                                <td>Nama :</td>
                                <td><s:text name="nama" id="nama" size="30"/></td>
                            </tr>
                            <tr>
                                <td> Aktif :</td>
                                <td><s:select name="aktif" id="aktif">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="Y">Y</s:option>
                                        <s:option value="T">T</s:option>
                                    </s:select></td>
                            </tr>
                            <tr>
                                <td>Dikemaskini Oleh :</td>
                                <td><s:text name="diMasukOleh" id="diMasukOleh" disabled="true"/></td>
                            </tr>
                        </table>

                    </div>

                    <center>
                        <s:submit name="updateData" id="simpan1" value="Simpan" class="btn"/>
                        <s:button name="ttp" value="Tutup" class="btn" onclick="tutup();"/>
                    </center>

                    <br>
                    <br>
                </fieldset>
            </div>
        </s:form>
    </body>
</html>