<%-- 
    Document   : latarbelakang
    Created on : May 3, 2010, 11:28:08 AM
    Author     : nurul.izza
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready( function() {
        var len = $(".daerah").length;
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });

    function addLatarbelakang(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showTambahLatarbelakangPemohon", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
    }

    function removeLatarbelakang(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function kemaskini(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showEditPemohon&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Butir-butir Suami/Isteri</legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Alamat Majikan" >${line.pihak.suratAlamat1}
                        <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat2}
                        <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat3}
                        <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat4}</display:column>
                    <display:column property="pihak.suratPoskod" title="Poskod" />
                    <display:column property="pihak.suratNegeri.nama" title="Negeri" />
                    <display:column title="Kemaskini"><a href="#" onclick="kemaskini('${line_rowNum -1}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removeLatarbelakang('${line.idPemohon}')">
                        </div>
                    </display:column>
                </display:table>
            </div>
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addLatarbelakang();"/>&nbsp;
            </p>
            <br>
            <p>
                <label>&nbsp;</label>
                <font color="blue"><b>Tanah Yang Dimiliki Suami/Isteri</b></font>
            </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikList}"  cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line2">
                    <display:column title="No" sortable="true">${line2_rowNum}</display:column>
                    <display:column property="idHakmilik" title="ID Hakmilik" class="popup hakmilik${line2_rowNum}"/>
                    <display:column property="noLot" title="No Lot" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line2.luas}"/>&nbsp;${line2.kodUnitLuas.nama}</display:column>
                    <display:column property="daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"/>
                </display:table>
            </div>
        </fieldset>
    </div>
</s:form>
