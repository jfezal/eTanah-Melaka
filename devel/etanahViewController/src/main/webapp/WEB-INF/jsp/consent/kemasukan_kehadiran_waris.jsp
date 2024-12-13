<%--
    Document   : kemasukan_senarai_waris
    Created on : Mar 17, 2010, 12:51:37 PM
    Author     : muhammad.amin
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.empty').remove();
    });

    function removeWaris(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/consent/kehadiran_waris?deleteWarisHadir&idMohonPihak='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function addNewWaris(){
        window.open("${pageContext.request.contextPath}/consent/kehadiran_waris?pilihWaris", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=909,height=800");
    }

    function refreshPage1(){
        var url = '${pageContext.request.contextPath}/consent/kehadiran_waris?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.consent.KehadiranWarisActionBean">

        <fieldset class="aras1">
            <legend>
                Senarai Kehadiran Waris
            </legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.warisHadirList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/consent/kemasukan_waris">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Alamat" >${line.pihak.suratAlamat1}
                        <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat2}
                        <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat3}
                        <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat4}</display:column>
                    <display:column property="pihak.suratPoskod" title="Poskod" />
                    <display:column property="pihak.suratNegeri.nama" title="Negeri" />

                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removeWaris('${line.idPermohonanPihak}')" onmouseover="this.style.cursor='pointer';">
                        </div>
                    </display:column>

                </display:table>
            </div>

            <p>
                <label>&nbsp;</label>
                <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewWaris();"/>&nbsp;
            </p>

        </fieldset>
        <br/>
    </s:form>

</div>
