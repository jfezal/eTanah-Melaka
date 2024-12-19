<%-- 
    Document   : batal_perserahan_utiliti_tukar_ganti
    Created on : Nov 21, 2016, 4:28:35 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Batal Perserahan Tukar Ganti (Utiliti) </title>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>     
        <script type="text/javascript">
    function clearForm(f) {
        $(f).clearForm();
        $("#line").hide();
        $("#refresh").hide();
    }

    function Batal(idMohon) {
        window.open("${pageContext.request.contextPath}/utiliti/batal_perserahan_utiliti_tukar_ganti?saveBatal&idPerserahan=" + idMohon, "eTanah",
                "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=900,height=350");

    }

    function reload(v)
    {
        var frm = document.form1;
        var url = "${pageContext.request.contextPath}/utiliti/batal_perserahan_utiliti_tukar_ganti?reload&idPerserahan=" + v;
        frm.action = url;
        frm.submit();

    }
        function save(event, f, idUrusan)
    {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idUrusan=' + idUrusan;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                    self.close();
                }, 'html');

    }

        </script>    
    </head>
    <body>

        <s:errors/>
        <s:messages/>        
        <div class="subtitle">            
            <s:form beanclass="etanah.view.utility.BatalPerserahanUtilitiTukarGanti" name="form1"> 
                <%--  <s:hidden name="idHakmilik" />--%>
                <fieldset class="aras1">
                    <legend>Utiliti Batal Perserahan Tukar Ganti (Utiliti)</legend>
                    <p>
                        <label>Id Perserahan :</label>
                        <s:text name="idTukarGanti" id="idTukarGanti" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cari" value="Cari" class="btn" />
                        <s:button class="btn" name="reset" value="Isi Semula" onclick="clearForm(this.form);"/>                   

                    </p>
                    <br> 
                    <br>  
                    <c:if test="${actionBean.showDetailPermohonan}">
                        <div class="subtitle">    
                            <fieldset class="aras1"> 
                                <center><display:table class="tablecloth" name="${actionBean.permohonan}" id="line">
                                        <display:column title="Id Perserahan" property="idPermohonan" />       
                                        <display:column title="Urusan" property="kodUrusan.kod" />  
                                        <display:column title="Dimasuk" property="infoAudit.dimasukOleh.nama" />  
                                        <display:column title="Status" property="status" />  
                                        <display:column title="Kemaskini Status">
                                            <td class="s">
                                                <s:select name="aktif">
                                                    <s:option value="0">Pilih</s:option>
                                                    <s:option value="SL">Selesai</s:option>
                                                    <s:option value="AK">Aktif</s:option>
                                                    <s:option value="TK">Tiada Kes</s:option>
                                                </s:select>
                                            </td>
                                        </display:column>
                                        <c:if test="${line.sebab != null}">
                                            <display:column title="Sebab Batal" property="sebab" />  
                                        </c:if>        
                                        <c:if test="${line.status eq 'TA'}">
                                            <display:column title="Batal Perserahan">                                             
                                                <p align="center"><img alt='Klik Untuk Batal' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                                       onclick="Batal('${line.idPermohonan}');"  onmouseover="this.style.cursor = 'pointer';">
                                                </p>
                                            </display:column>
                                        </c:if>
                                        <display:column title="Status">

                                        </display:column>
                                    </display:table>
                                    <br>
                                    <p> 
                                        <s:submit name="saveUrusanNBatal" value="Simpan" class="btn" onmouseover="this.style.cursor='pointer';" onclick="save(this.name, this.form)"/>
                                        <s:button id="refresh" name="" value="Klik Untuk Refresh" class="longbtn" onmouseover='this.style.cursor="pointer";' onclick="reload('${actionBean.permohonan.idPermohonan}')"/>
                                    </p>
                            </fieldset>
                        </div>
                    </c:if>
                </fieldset>
            </s:form>
        </div>
    </body>
</html>

