<%-- 
    Document   : nota_daftar_urusanterlibat
    Created on : Dec 29, 2009, 9:16:30 AM
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

<script language="javascript">
    function popup(f){
        var url = f.action + '?popPup&idPermohonan='+$("#idP").val()+"&idHakmilik="+$("#idH").val();
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=130");

    }
</script>


<s:form name="form1" beanclass="etanah.view.stripes.nota.NotaBatalActionBean">

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
            <div class="content" align="center">
                    <display:table name="${actionBean.hakmilikPermohonanList}" id="line1" class="tablecloth" >
                           <display:column title="No">${line1_rowNum}</display:column>
                               <display:column property="hakmilik.idHakmilik" title="Nama"/>
                               <%--<display:column property="pihak.noPengenalan" title="Nombor Pengenalan"/>
                               <display:column property="pihak.rumahAlamat1"  title="Alamat 1" />
                               <display:column property="pihak.rumahAlamat2" title="Alamat 2" />
                               <display:column property="pihak.rumahAlamat3" title="Alamat 3" />
                               <display:column property="pihak.rumahAlamat4" title="Alamat 4" />
                               <display:column property="pihak.rumahPoskod" title="Poskod" />
                               <display:column property="pihak.rumahNegeri.kod" title="Negeri" />--%>
                        </display:table>

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
