<%--
    Document   : edit_waris
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

            $(document).ready( function(){


                $('input').focus(function() {
                    $(this).addClass("focus");
                });

                $('input').blur(function() {
                    $(this).removeClass("focus");
                });

                $('select').focus(function() {
                    $(this).addClass("focus");
                });

                $('select').blur(function() {
                    $(this).removeClass("focus");
                });
            });

            function save(event, f, idWaris, idHakmilikPihakBerkepentingan)
            {

                var q = $(f).formSerialize();
                var url = f.action + '?' + event+'&idWaris='+idWaris+ '&idHakmilikPihakBerkepentingan='+idHakmilikPihakBerkepentingan ;

                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                }, 'html');

            }
        </script>
    </head>
    <body>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
        <s:form name="form1" beanclass="etanah.view.stripes.nota.PihakBerkepentinganActionBean">

            <s:messages/>
            <s:errors/>
            <div class="subtitle">
                <div class="content" align="center">
                    <fieldset class="aras1">
                        <table class="tablecloth" width="70%">
                            <tr><th colspan="3">Maklumat Waris</th></tr>
                            <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>
                            <tr><td>Nama :</td><td class="s">${actionBean.waris.nama}</td>
                                <td class="s">
                                    <s:text name="nama" style="width:98% !important;"/>
                                </td></tr>

                            <tr><td>Jenis Pengenalan :</td><td class="s">${actionBean.waris.jenisPengenalan.nama}</td>
                                <td class="s">
                                    <s:select name="jenisPengenalan" id="" value="kod" >
                                        <s:option value="">Pilih ...</s:option>
                                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                                    </s:select>
                                </td></tr>

                            <tr><td>No. Pengenalan :</td><td class="s">${actionBean.waris.noPengenalan}</td>
                                <td class="s">
                                    <s:text name="noPengenalan"/>
                                </td></tr>

                            <tr><td>Pembilang / Penyebut :</td><td class="s">${actionBean.hpBerkepentingan.syerPembilang}/${actionBean.hpBerkepentingan.syerPenyebut}</td>
                            <td class="s">
                                <s:text name="pembilang" style="width:46% !important;"/> / <s:text name="penyebut" style="width:46% !important;"/>
                            </td></tr>
                            <tr><td>Negeri :</td><td class="s">${actionBean.waris.negeri.nama}</td>
                                <td class="s">
                                    <s:select name="negeri" id="negeri" value="kod">
                                        <s:option value="">Pilih ...</s:option>
                                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                                    </s:select>
                                </td></tr>
                            <tr><td>Warganegara :</td><td class="s">${actionBean.waris.wargaNegara.nama}</td>
                                <td class="s">
                                    <s:select name="warganegara" id="pemilikWarganegara" value="kod">
                                        <s:option value="">Pilih ...</s:option>
                                        <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod" />
                                    </s:select>
                                </td></tr>
                            <tr><td colspan="3">
                                    <div align="center">
                                        <s:button name="saveBetulPihakWaris" value="Simpan" class="btn" onmouseover="this.style.cursor='pointer';" onclick="save(this.name, this.form, '${actionBean.waris.idWaris}', '${actionBean.waris.pemegangAmanah.idHakmilikPihakBerkepentingan}')"/>
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
