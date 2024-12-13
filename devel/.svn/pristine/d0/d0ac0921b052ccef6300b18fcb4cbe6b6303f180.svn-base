<%--
    Document   : popup_editAsas
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : mohd.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;
    }
</style>

<script language="javascript">
    $(document).ready(function() {
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
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


    function save(event, f, idH)
    {



        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idH=' + idH;

        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                }, 'html');

        $.prompt('Maklumat Berjaya Disimpan.', {
            buttons: {Ok: true},
            prefix: 'jqismooth',
            submit: function(v, m, f) {
                self.close();
            }
        });

    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">

    <s:messages />
    <s:errors />

    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Asal
            </legend>
            <p style="color:red">
                *Isi ruang pembetulan kemudian tekan butang simpan.<br/>

            </p>
            <div class="content" align="center" id="tanahMilik">

                <table class="tablecloth" >
                    <tr><th colspan="3">ID Hakmilik - ${actionBean.hakmilik.idHakmilik}</th></tr>
                    <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>
                    <tr><td>ID Hakmilik Asal :</td><td class="s">${actionBean.hakmilikAsal.hakmilikAsal}</td><td><s:text name="permohonanPembetulanHakmilik.idHakmilikAsal"/></td></tr>

                    <td>Tarikh Pemilikan :</td><td class="s"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanPembetulanHakmilik.tarikhDaftarHakmilikAsal}"/></td> 

                    <tr>
                        <td class="s">

                            <s:text name="tarikhDaftarHakmilikAsal" id="datepicker" class="datepicker" onfocus=""/>

                        </td></tr>

                </table>
            </div>

            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div >

                            <s:button name="saveBetulAsal" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.hakmilik.idHakmilik}')"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>

                        </div>
                    </td>
                </tr>
            </table>
            <br/>

        </fieldset>
    </div>

</s:form>