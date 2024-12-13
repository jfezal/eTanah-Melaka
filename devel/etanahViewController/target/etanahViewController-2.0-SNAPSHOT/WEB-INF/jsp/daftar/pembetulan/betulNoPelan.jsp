<%--
    Document   : betulTarikhLuput
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
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
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }
</style>

<script type="text/javascript">

    function editPop(idH)
    {
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?popupAsas&idH='+idH;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=550,scrollbars=yes");
    }

       function viewHakmilik(id){
        window.open("${pageContext.request.contextPath}/daftar/kesinambungan?viewhakmilikDetail&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">

    <s:messages />
    <s:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat No. Pelan
            </legend>
            <p style="color:red">
                *Isi ruang pembetulan kemudian tekan butang simpan.<br/>
            </p>
            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column   title="ID Hakmilik" >
                        <a href="#" onclick="viewHakmilik('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a>
                    </display:column>
                    <display:column property="hakmilik.noPelan"  title="No Pelan"/>
                    <display:column title="No Pelan Baru">
                        <s:text name="noPelan[${line_rowNum-1}]"/>
                    </display:column>
                </display:table>
                <br>
                <p>
                    <s:button name="savePelan" value="Simpan" class="btn" onclick="if(confirm('Adakah anda pasti?'))doSubmit(this.form, this.name,'page_div');"/>
                </p>
                <br/>
            </div>
        </fieldset>
    </div>
</s:form>