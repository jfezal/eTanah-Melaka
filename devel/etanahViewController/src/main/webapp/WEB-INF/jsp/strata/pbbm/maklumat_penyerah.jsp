<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
   Modified by : Murali oct 24, 2011
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function simpanPenyerah(event, f){      
        if(document.getElementById("check").checked ==false){
            if(confirm('Adakah anda pasti penyerah bukan pemohon?')) {
                var q = $(f).formSerialize();
                var url = '${pageContext.request.contextPath}/strata/maklumat_penyerah?deletePemohonBukanPenyerah';
                $.get(url,q,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }else{document.getElementById("check").checked=true;
            }
        }
        if(document.getElementById("check").checked ==true){
            if(confirm('Adakah anda pasti penyerah adalah pemohon?')) {
                var q = $(f).formSerialize();
                var url = '${pageContext.request.contextPath}/strata/maklumat_penyerah?copyPenyerah';
                $.get(url,q,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }else{document.getElementById("check").checked=false;
            }
        }
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.strata.MaklumatPenyerahActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend> 

            <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'B' or actionBean.permohonan.penyerahJenisPengenalan.kod eq 'b'}">
                <p>
                    <label>Jenis Penyerah :</label>
                    Individu/Syarikat&nbsp;
                </p>

                <p>
                    <label>No.Pengenalan :</label>
                    ${actionBean.permohonan.penyerahNoPengenalan}&nbsp;
                </p>

                <p>
                    <label>No.Rujukan Penyerah :</label>
                    ${actionBean.permohonan.idPenyerah}&nbsp;
                </p>
            </c:if>

            <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod ne 'B' and actionBean.permohonan.penyerahJenisPengenalan.kod ne 'b'}">
                <c:if test="${actionBean.permohonan.kodPenyerah ne null}">
                    <p>
                        <label>Jenis Penyerah :</label>
                        ${actionBean.permohonan.kodPenyerah.nama}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.idPenyerah ne null}">
                    <p>
                        <label>No.Rujukan Penyerah :</label>
                        ${actionBean.permohonan.idPenyerah}&nbsp;
                    </p>
                </c:if>
            </c:if>
            <p>
                <label>Nama :</label>
                ${actionBean.permohonan.penyerahNama}&nbsp;
            </p>

            <p>
                <label>Alamat :</label>
                ${actionBean.permohonan.penyerahAlamat1}&nbsp;                
            </p>

            <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}">
                <p>
                    <label>&nbsp;</label>
                    ${actionBean.permohonan.penyerahAlamat2}&nbsp;
                </p>
            </c:if>           

            <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}">
                <p>
                    <label>&nbsp;</label>
                    ${actionBean.permohonan.penyerahAlamat3}&nbsp;
                </p>
            </c:if>
            <%--<c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}">--%>
            <p>
                <label>Bandar :</label>
                ${actionBean.permohonan.penyerahAlamat4}&nbsp;

            </p>
            <%--</c:if>--%>
            <p>
                <label>Poskod :</label>
                ${actionBean.permohonan.penyerahPoskod}&nbsp;
            </p>

            <p>
                <label>Negeri :</label>
                <%--${actionBean.permohonan.penyerahNegeri.nama}&nbsp;--%>
                ${actionBean.negeri}&nbsp;
            </p>
            <c:if test="${actionBean.permohonan.penyerahNoTelefon1 ne null}">
                <p>
                    <label>No.Telefon :</label>
                    ${actionBean.permohonan.penyerahNoTelefon1}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahEmail ne null}">
                <p>
                    <label>Email :</label>
                    ${actionBean.permohonan.penyerahEmail}&nbsp;
                </p>
            </c:if>            
            <p></p>
            <p></p>
            <%--<c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PHPC' && actionBean.permohonan.kodUrusan.kod ne 'PHPP'}">--%>
            <c:if test="${actionBean.rayuan}">
                <p><label>&nbsp;</label>
                    <s:checkbox name="penyerah" id="check" value="${actionBean.penyerah}"onclick="simpanPenyerah(this.name, this.form);" ></s:checkbox>
                        Penyerah adalah Pemohon
                    </p>
            </c:if>
            <%--</c:if>--%>
        </fieldset>
    </div>
</s:form>