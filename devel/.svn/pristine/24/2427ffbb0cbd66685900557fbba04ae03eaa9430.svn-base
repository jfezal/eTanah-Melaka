<%-- 
    Document   : pilih_penempatan_peserta
    Created on : Sep 14, 2012, 3:33:45 PM
    Author     : Navin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE html>
<script type="text/javascript">
    function add(){
        window.open("${pageContext.request.contextPath}/pelupusan/penempatan_peserta?addPnmptnPsrta", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }
    function update(i){
            var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pelupusan/penempatan_peserta?updatePnmptnPsrta&idPnmptnPsrta="+i, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }
    function updateLot(i){
            var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pelupusan/penempatan_peserta?updateLotPnmptnPsrta&idPnmptnPsrta="+i, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }    
     
    function deletePnmptnPsrta(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/penempatan_peserta?deletePnmptnPsrta&idPnmptnPsrta='+val;
            $.get(url,function(data){$('#page_div').html(data);},'html');
        }
    }

     function refreshPnmptnPsrta(){
        var url = '${pageContext.request.contextPath}/pelupusan/penempatan_peserta?refreshMaklumatPenempatan';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function refreshMklmtLot(){
        var url = '${pageContext.request.contextPath}/pelupusan/penempatan_peserta?refreshMaklumatLot';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.PenempatanPesertaActionBean">
    <fieldset class="aras1">
    <legend>Butir-butir Penempatan Peserta</legend>

    <div class="content" align="center">
        <display:table class="tablecloth" name="${actionBean.penempatanPesertaList}" cellpadding="0" cellspacing="0" id="line"
                       requestURI="${pageContext.request.contextPath}/pelupusan/penempatan_peserta">

            <display:column title="Bil">${line_rowNum}
                <s:hidden name="" class="${line_rowNum -1}" value="${line.idPenempatanPeserta}"/>
            </display:column>
            <display:column property="nama" title="Nama"/>
            <display:column property="noPengenalan" title="Nombor Pengenalan" />

            <display:column title="Alamat" >${line.alamat1}
                <c:if test="${line.alamat2 ne null}"> , </c:if>
                ${line.alamat2}
                <c:if test="${line.alamat3 ne null}"> , </c:if>
                ${line.alamat3}
                <c:if test="${line.alamat4 ne null}"> , </c:if>
                ${line.alamat4}</display:column>
            <display:column property="poskod" title="Poskod" />
            <display:column property="negeri.nama" title="Negeri" />
            <display:column property="markahTemuduga" title="Markah Temuduga" />
           <c:if test="${actionBean.forLot}">
                <display:column property="noLot" title="No Lot" />
            </c:if> 
               
                <c:if test="${actionBean.forLot}">
                <display:column title="Kemaskini Untuk Lot"><a href="#" onclick="updateLot('${line.idPenempatanPeserta}');return false;">Kemaskini</a></display:column>
                </c:if>
                <c:if test="${edit eq true}">
                    <c:if test="${!actionBean.forLot}">
                        <display:column title="Kemaskini"><a href="#" onclick="update('${line.idPenempatanPeserta}');return false;">Kemaskini</a></display:column>
                    </c:if>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="deletePnmptnPsrta('${line.idPenempatanPeserta}')">
                        </div>
                        
                    </display:column>
                </c:if> 
        </display:table>
        <c:if test="${edit eq true}">
                        <s:button class="btn" value="Tambah" name="new2" id="new2"  size="60" onclick="add();"/>&nbsp;
        </div>
    </c:if>
    </fieldset>
</s:form>