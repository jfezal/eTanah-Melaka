<%-- 
    Document   : pembetul_maklumatplan
    Created on : Dec 24, 2009, 12:01:58 PM
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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script language="javascript">
    function popup(f){
        var url = "http://www.dow.cam.ac.uk/dow_server/maps/Porters_plan.gif";//f.action + '?popupSebelumkini&idPermohonan='+$("#idP").val()+"&idHakmilik="+$("#idH").val();
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=800");

    }
</script>


<s:form beanclass="etanah.view.stripes.nota.pembetulanBeanActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Perihal Pembetulan
            </legend>
            <p style="color:red">
                *Klik browse untuk muatnaik imej plan.<br/>
                *Klik papar untuk melihat imej plan.
            </p>
            <p>
                <s:label name="dokumen">ID Hakmilik :</s:label>
                070203GM00001616
            </p>
            <p>
                <s:label name="dokumen">Muatnaik Plan :</s:label>
                <s:file name="uploadPlan"/>
             
            </p>
            <br/>
             <table style="margin-left: auto; margin-right: auto;">
                    <tr>
                        <td>&nbsp;</td>
                        <td><div >

                                <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="savePengambilan" value="Simpan"/>


                            </div>
                        </td>
                    </tr>
                </table>
<div class="content" align="center">
                <%--<display:table class="tablecloth" name="" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Pilih" sortable="true" ></display:column>
                    <display:column title="Pengenalan" />
                    <display:column title="Nama Pemberi"/>
                    <display:column title="padam">test
                    </display:column>
                </display:table>--%>

                <table class="tablecloth">
                    <tr><th>Papar</th><th>Bil.</th><th>Imej Plan</th><th>Hapus</th></tr>
                    <tr><td><blink> <s:button name="hapus" value="Papar" onclick="popup(this.form);"/></blink></td><td>1</td><td>plan1.jpg</td><td><s:button name="hapus" value="Hapus"/></td></tr>
                </table>
            </div>
        </fieldset>
                                <br/>
    </div>

    <br>
</s:form>