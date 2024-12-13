<%--
    Document   : betulBatalEndosan
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
    function batal(){
        var len = $('.nama').length;
        var param = '';

        for(var i=1; i<=len; i++){
            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + '&idUrusan=' + $('#chkbox'+i).val();
            }
        }
        if(param == ''){
            alert('Tiada Urusan.');
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?saveBatalEndosan'+param;

        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
            },
            success : function(data){
                $('#page_div').html(data);
            }
        });
    }


    function removeChanges(val){
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer){
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deleteChanges&idUrusan='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            });
        }
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">

    <s:messages />
    <s:errors />

    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Maklumat Urusan SC/B/N
            </legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikUrusanListByKodserah}" cellpadding="0" cellspacing="0" id="line">
                    <display:column>
                        <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.idUrusan}"/>
                    </display:column>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idPerserahan" title="ID Perserahan" class="nama" />
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="kodUrusan.kod" title="Kod Urusan" />
                    <display:column property="kodUrusan.nama" title="Urusan" />
                    <display:column property="tarikhDaftar" title="Tarikh Daftar" format="{0,date,dd-MM-yyyy}"/>
                </display:table>
            </div>
            <p>
                <label>&nbsp;</label>
                <s:button name="saveBatalEndosan" value="Pilih" class="btn" onclick="batal();"/>
            </p>
            <br/>
            <c:if test="${fn:length(actionBean.mohonAtasUrusan) > 0}">
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.mohonAtasUrusan}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="urusan.kodUrusan.nama" title="Nama Urusan" sortable="true"/>
                         <display:column property="urusan.idPerserahan" title="ID Perserahan" sortable="true"/>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh Permohonan" format="{0,date,dd-MM-yyyy}"/>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem${line_rowNum}' onclick="removeChanges('${line.urusan.idUrusan}')" onmouseover="this.style.cursor='pointer';" >
                            </div>
                        </display:column>
                    </display:table>
                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>