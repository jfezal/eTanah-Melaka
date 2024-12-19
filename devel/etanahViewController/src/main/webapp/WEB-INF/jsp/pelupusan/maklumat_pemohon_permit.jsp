<%-- 
    Document   : maklumat_pemohon_permit
    Created on : Apr 21, 2010, 5:30:45 PM
    Author     : sitifariza.hanim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

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

    function addPemohon(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_permit?showTambahPemohon", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
    }

    function removePemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_permit?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function kemaskini(id){
        <%--var d = $('.x'+i).val();--%>
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_permit?showEditPemohon&idPihak="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

     function refreshPage(){
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_permit?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
    }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPermohonPermitActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Pemohon</legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/maklumat_pemohon_permit">

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
                    <display:column title="Kemaskini"><a href="#" onclick="kemaskini('${line.pihak.idPihak}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')">
                        </div>
                    </display:column>
                </display:table>
            </div>
             <c:if test="${edit}">
            <p>
                <label>&nbsp;</label>
               
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addPemohon();"/>&nbsp;
                
            </p>
            </c:if>
            <br>
            <p>
                <label>&nbsp;</label>
                <font color="blue"><b>Tanah Yang Dimiliki Pemohon</b></font>
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

