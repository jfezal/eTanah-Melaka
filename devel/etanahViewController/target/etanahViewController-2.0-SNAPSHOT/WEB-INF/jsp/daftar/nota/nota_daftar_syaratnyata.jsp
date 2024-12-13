<%-- 
    Document   : nota_daftar_syaratnyata
    Created on : Dec 28, 2009, 6:03:22 PM
    Author     : mohd.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }
</style>




<s:form name="form1" beanclass="etanah.view.stripes.nota.NotaDaftarActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Maklumat Baru
            </legend>
            <%--<p style="color:red">
                *Pilih maklumat berkenaan sahaja untuk membuat pembetulan.<br/>

            </p>--%>
            <div class="content" align="center" id="tanahMilik">

                <table class="tablecloth">
                    <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>
                    <tr><td class="s">Kod Syarat Nyata :</td><td>${actionBean.hakmilikPermohonan.hakmilik.syaratNyata.kod}</td><td><s:text name="kodSyarat"/></td>Pilih Kod Syarat Nyata</tr>
                    <tr><td class="s">Kod Sekatan Kepentingan:</td><td>${actionBean.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod}</td><td><s:text name="kodNoLot"/></td>Pilih Kod Sekatan Kepentingan</tr>
                    <tr><td class="s">Cukai (RM) :</td><td>${actionBean.hakmilikPermohonan.hakmilik.cukai}</td><td><s:text name="unitKeluasan"/></td></tr>
                  </table>
            </div>

            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div >

                            <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="save" value="Simpan"/>


                        </div>
                    </td>
                </tr>
            </table>
            <br/>

        </fieldset>
    </div>

</s:form>