<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {

        $("#add").click( function(){
            frm = this.form;
            //TODO :
            var len = $(".chkbox").length;

            for(var i=1; i<=len; i++){
                if($('#chkbox'+i).is(':checked')){
                    var idPihak = $('#chkbox'+i).val();
                    var nama = $(".nama"+i).text();
                    var noPengenalan = $(".noPengenalan"+i).text();
                    <%--var suratAlamat1 = $(".suratAlamat1"+i).text();
                    var suratAlamat2 = $(".suratAlamat2"+i).text();
                    var suratAlamat3 = $(".suratAlamat3"+i).text();
                    var suratAlamat4 = $(".suratAlamat4"+i).text();
                    var suratPoskod = $(".suratPoskod"+i).text();
                    var suratNegeri = $(".suratNegeri"+i).text();--%>
                    var alamat = $(".alamat"+i).text();
                    var noTelefon1 = $(".noTelefon1"+i).text();
                    var email = $(".email"+i).text();
                    <%--var alamat = "";
                    if(suratAlamat1 != null)
                        alamat += suratAlamat1+", ";
                    if(suratAlamat2 != null)
                        alamat += suratAlamat2+", ";
                    if(suratAlamat3 != null)
                        alamat += suratAlamat3+", ";
                    if(suratAlamat4 != null)
                        alamat += suratAlamat4+", ";
                    if(suratPoskod != null)
                        alamat += suratPoskod+", ";
                    if(suratNegeri != null)
                        alamat += suratNegeri;--%>

                        var a = "";
                  <%--$.get("${pageContext.request.contextPath}/common/maklumat_pemohon?simpanPemohon&idPihak="+idPihak,
                        function(data){                           
                            a = data;
                            self.opener.addRows(nama, noPengenalan, idPihak, $('#idPermohonan').val(), alamat, noTelefon1, a);
                        });--%>
                        
                        
                    self.opener.addRows(nama, noPengenalan, idPihak, $('#idPermohonan').val(), alamat, noTelefon1, email, v(idPihak));
                    

                }
            }
            setTimeout(function(){
                self.opener.refreshPage();
                self.close();
            }, 500);
            
        });
    });

    function v(va) {
        var a="";
        $.get("${pageContext.request.contextPath}/common/maklumat_pemohon?simpanPemohon&idPihak="+va,
                        function(data){
                            a = data;
                            
                        });

                        return a;

    }

</script>

<s:form beanclass="etanah.view.stripes.common.MaklumatPemohonActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tuan Tanah / Pihak Berkepentingan
            </legend>

            <p>
                <label>&nbsp;</label>
                <s:button name="" id="add" value="Pilih" class="btn"/>&nbsp;
                <s:button name="" value="Tutup" class="btn" onclick="self.close();"/>
            </p>

            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.listTuanTanah}" cellpadding="0" cellspacing="0" requestURI="/common/maklumat_pemohon"  id="line">
                    <display:column> <s:checkbox name="chkbox" id="chkbox${line_rowNum}" value="${line.idPihak}" class="chkbox" onmouseover="this.style.cursor='pointer';"/></display:column>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="nama" title="Nama" class="nama${line_rowNum}"/>
                    <display:column property="noPengenalan" title="No. Pengenalan" class="noPengenalan${line_rowNum}"/>
                    <%--<display:column property="suratAlamat1"  title="Alamat 1" class="suratAlamat1${line_rowNum}"/>
                    <display:column property="suratAlamat2" title="Alamat 2" class="suratAlamat2${line_rowNum}"/>
                    <display:column property="suratAlamat3" title="Alamat 3" class="suratAlamat3${line_rowNum}"/>
                    <display:column property="suratAlamat4" title="Alamat 4" class="suratAlamat4${line_rowNum}"/>
                    <display:column property="suratPoskod" title="Poskod" class="suratPoskod${line_rowNum}"/>
                    <display:column property="suratNegeri.nama" title="Negeri" class="suratNegeri${line_rowNum}"/>--%>
                    <display:column title="Alamat" class="alamat${line_rowNum}">
                        ${line.suratAlamat1}<c:if test="${line.suratAlamat2 ne null}">,</c:if>
                        ${line.suratAlamat2}<c:if test="${line.suratAlamat3 ne null}">,</c:if>
                        ${line.suratAlamat3}<c:if test="${line.suratAlamat4 ne null}">,</c:if>
                        ${line.suratAlamat4}<c:if test="${line.suratPoskod ne null}">,</c:if>
                        ${line.suratPoskod}<c:if test="${line.suratNegeri.kod ne null}">,</c:if>
                        ${line.suratNegeri.nama}
                    </display:column>
                    <display:column property="noTelefon1" title="No. Telefon" class="noTelefon1${line_rowNum}"/>
                    <display:column property="email" title="Alamat Email" class="email${line_rowNum}"/>
                    <c:forEach var="hakmilikPihak" items="${actionBean.senaraiHakmilikPihak}">
                        <c:if test="${hakmilikPihak.pihak.idPihak eq line.idPihak}">
                            <display:column title="Jenis PB">${hakmilikPihak.jenis.nama}</display:column>
                            <display:column title="Syer">${hakmilikPihak.syerPembilang}/${hakmilikPihak.syerPenyebut}</display:column>
                        </c:if>
                    </c:forEach>
                </display:table>

            </div>
        </fieldset>
    </div>
</s:form>
