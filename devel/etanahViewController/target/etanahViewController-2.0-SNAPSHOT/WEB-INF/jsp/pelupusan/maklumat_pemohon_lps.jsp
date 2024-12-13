<%-- 
    Document   : maklumat_pemohon_lps
    Created on : Feb 11, 2010, 12:15:37 PM
    Author     : muhammad.amin
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

<script type="text/javascript">

    $(document).ready(function() {
        var len = $(".daerah").length;
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }

    });


    $('#content1').hide();

    $('a').click(function(){

        $('#content1').show('slow');

    });

    function refreshMaklumatPemohon(){
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?refreshMaklumatPemohon';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function addPemohon(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?showTambahPemohon", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }

    function addPemohonSuami_Isteri(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?showTambahLatarbelakangPemohon", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }

    function addPemohonAnak(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?showMaklumatAnak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }
    function addPemohonLembaga_Pengarah(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?showMaklumatAhliLembaga", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }
    function removePemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?deletePemohon&idPemohon='+val;
            $.get(url,function(data){$('#page_div').html(data);},'html');
        }
    }

    function removePemohonHbgn(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?deletePemohonHbgn&idPemohonHbgn='+val;
            $.get(url,function(data){$('#page_div').html(data);},'html');
        }
    }

    function removePengarah(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?deletePihakPengarah&idPengarah='+val;
            $.get(url,function(data){$('#page_div').html(data);},'html');
        }
    }

    function kemaskini(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?showEditPemohon&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600, scrollbars=yes");
    }
    function kemaskini1(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?showEditSuamiIsteri&idHubungan="+i, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function kemaskiniAnak(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?showEditAnak&idHubungan="+i, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function kemaskinilembaga(i){
    <%-- var d = $('.x'+i).val();--%>
               window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps?showEditlembaga&idPengarah="+i, "eTanah",
               "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
           }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonLPSActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Pemohon</legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Alamat" >${line.pihak.alamat1}
                        <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                        ${line.pihak.alamat2}
                        <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                        ${line.pihak.alamat3}
                        <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                        ${line.pihak.alamat4}</display:column>
                    <display:column property="pihak.poskod" title="Poskod" />
                    <display:column property="pihak.negeri.nama" title="Negeri" />
                    <display:column title="Kemaskini"><a href="#" onclick="kemaskini('${line_rowNum -1}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')">
                        </div>
                    </display:column>
                </display:table>
                <tr><td>
                        <c:if test="${line.pihak eq null}">
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="addPemohon();"/>&nbsp;
                        </c:if>
                    </td></tr>
            </div>

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
            <br/>

            <%--Code Changed by rohan--%>

            <c:if test="${line.pihak ne null}">
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'|| actionBean.pihak.jenisPengenalan.kod eq 'L'|| actionBean.pihak.jenisPengenalan.kod eq 'P'||actionBean.pihak.jenisPengenalan.kod eq 'T'||actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'N'||actionBean.pihak.jenisPengenalan.kod eq 'F'}">
                    <legend>Butir-butir Suami/Isteri</legend>

                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.pemohonHubunganList}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps">

                            <display:column title="Bil" sortable="true">${line_rowNum}
                                <s:hidden name="" class="${line_rowNum -1}" value="${line.idHubungan}"/>
                            </display:column>
                            <display:column property="nama" title="Nama"/>
                            <display:column property="noPengenalan" title="Nombor Pengenalan" />

                            <display:column title="Alamat Majikan" >${line.alamat1}
                                <c:if test="${line.alamat2 ne null}"> , </c:if>
                                ${line.alamat2}
                                <c:if test="${line.alamat3 ne null}"> , </c:if>
                                ${line.alamat3}
                                <c:if test="${line.alamat4 ne null}"> , </c:if>
                                ${line.alamat4}</display:column>
                            <display:column property="poskod" title="Poskod" />
                            <display:column property="negeri.nama" title="Negeri" />
                            <display:column title="Kemaskini"><a href="#" onclick="kemaskini1('${line.idHubungan}');return false;">Kemaskini</a></display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePemohonHbgn('${line.idHubungan}')">
                                </div>

                            </display:column>
                        </display:table>
                        <%-- value : ${actionBean.pemohon.pihak.kodJantina}--%>
                      <c:if test="${actionBean.pemohon.pihak.kodJantina eq '1'}">
                          <c:if test="${fn:length(actionBean.pemohonHubunganList) < 4}">
                        <tr><td>
                                <s:button class="btn" value="Tambah" name="new1" id="new1"  size="60" onclick="addPemohonSuami_Isteri();"/>&nbsp;
                            </td></tr>
                          </c:if>
                      </c:if>
                      <c:if test="${actionBean.pemohon.pihak.kodJantina eq '2'}">
                          <c:if test="${fn:length(actionBean.pemohonHubunganList) < 1}">
                        <tr><td>
                                <s:button class="btn" value="Tambah" name="new1" id="new1"  size="60" onclick="addPemohonSuami_Isteri();"/>&nbsp;
                            </td></tr>
                          </c:if>
                      </c:if>
                    </div>
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
                    <br/>

                    <br/>
                    <legend>Butir-butir Anak</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.pemohonHubunganList1}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps">

                            <display:column title="Bil" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idHubungan}"/>
                            </display:column>
                            <display:column property="nama" title="Nama Anak Pemohon"/>
                            <display:column property="umur" title="Umur" />
                            <display:column title="Nama Sekolah" property="institusi"/>
                            <display:column title="Kemaskini"><a href="#" onclick="kemaskiniAnak('${line.idHubungan}');return false;">Kemaskini</a></display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePemohonHbgn('${line.idHubungan}')">
                                </div>
                            </display:column>
                        </display:table>
                        <tr><td>
                                <s:button class="btn" value="Tambah" name="new2" id="new2" onclick="addPemohonAnak();"/>&nbsp;
                            </td></tr>
                    </div>
                </c:if>

                <br/><br/>
               <%--     ${actionBean.pihak.jenisPengenalan.kod}....${actionBean.pihak}--%>
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                    <legend>Butir-butir Ahli Lembaga Pengarah</legend>
                    <div class="content" align="center">

                        <display:table class="tablecloth" name="${actionBean.pihakPengarahList}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon_lps">

                            <display:column title="Bil" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPengarah}"/>
                            </display:column>
                            <display:column property="nama" title="Nama Ahli"/>
                            <display:column property="noPengenalan" title="Nombor Pengenalan" />
                            <display:column title="Alamat" >${line.pihak.alamat1}
                                <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                                ${line.pihak.alamat2}
                                <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                                ${line.pihak.alamat3}
                                <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                                ${line.pihak.alamat4}</display:column>
                            <display:column property="pihak.poskod" title="Poskod" />
                            <display:column property="pihak.negeri.nama" title="Negeri" />
                            <%--<display:column property="pihak.jumSaham" title="Jumlah Saham"/>--%>
                            <display:column title="Kemaskini"><a href="#" onclick="kemaskinilembaga('${line.idPengarah}');return false;">Kemaskini</a></display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePengarah('${line.idPengarah}')">
                                </div>
                            </display:column>
                        </display:table>

                        <tr><td>
                                <s:button class="btn" value="Tambah" name="new3" id="new3" onclick="addPemohonLembaga_Pengarah();"/>&nbsp;
                            </td></tr>
                    </div>
                </c:if>
            </c:if>
        </fieldset>
    </div>
</s:form>


