<%--
    Document   : edit_SuratKuasaWakil
    Created on : Jul 19, 2010, 7:24:45 PM
    Author     : wan.fairul
--%>


<html>
    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
        <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
        <script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
        <script type="text/javascript">
            $(document).ready(function() {

                $('input:text').each(function(){
                    $(this).focus(function() { $(this).addClass('focus')});
                    $(this).blur(function() { $(this).removeClass('focus')});
                });


            });

            function isNumberKey(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 45 || charCode > 57 || charCode == 47))
                    return false;

                return true;
            }

        </script>
        <script language="javascript">
            function save(event, f, idPenerima)
            {
                var q = $(f).formSerialize();
                var url = f.action + '?' + event+'&idPenerima='+idPenerima ;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                },'html');

            }
        </script>
    </head>
    <body>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
        <s:form name="form1" beanclass="etanah.view.stripes.nota.BetulSuratKuasaWakilActionBean">

            <s:messages/>
            <s:errors/>
            <div class="subtitle">
                <div class="content" align="center">
                    <fieldset class="aras1">
                        <table class="tablecloth" width="70%">
                            <tr><th colspan="3">Maklumat Penerima</th></tr>
                            <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>
                            <tr><td>Nama :</td><td class="s">${actionBean.wakilPenerima.nama}</td>
                                <td class="s">
                                    <s:text name="nama"/>
                                </td></tr>

                            <tr><td>Jenis Pengenalan :</td><td class="s">${actionBean.wakilPenerima.jenisPengenalan.nama}</td>
                                <td class="s">
                                    <s:select name="jenisPengenalan" id="" value="kod" >
                                        <s:option value="">Pilih ...</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                                    </s:select>
                                </td></tr>

                            <tr><td>No. Pengenalan :</td><td class="s">${actionBean.wakilPenerima.noPengenalan}</td>
                                <td class="s">
                                    <s:text name="noPengenalan"/>
                                </td></tr>

                            <tr><td>Amaun (RM) :</td><td class="s">${actionBean.wakilPenerima.amaunMaksima}</td>
                                <td class="s">
                                    <s:text name="amaunMaksima"/>
                                </td></tr>


                            <tr><td colspan="3">
                                    <div align="center">
                                        <s:button name="saveBetulSW" value="Simpan" class="btn" onmouseover="this.style.cursor='pointer';" onclick="save(this.name, this.form, '${actionBean.wakilPenerima.idPenerima}')"/>
                                        <s:button name="tutup" value="Tutup" class="btn" onmouseover="this.style.cursor='pointer';" onclick="javascript:self.close()"/>
                                    </div>
                                </td></tr>
                        </table>
                    </fieldset>
                </div>

            </div>
        </s:form>


    </body>
</html>
