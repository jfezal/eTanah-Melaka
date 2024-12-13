<%--
    Document   : maklumat_pemohon
    Created on : Feb 11, 2010, 12:15:37 PM
    Author     : muhammad.amin
    Modify by  : Siti Fariza Hanim (May 20, 2010)
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

<script type="text/javascript">

     function refreshPageMaklumatPemohon(){
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?refreshpage';
        $.get(url,
        function(data){
        $('#page_div').html(data);
        },'html');
     }

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

        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showTambahPemohon", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1010,height=600,scrollbars=yes");

    }

    function addPemohon1(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showTambahMaklumatIsteri", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=810,height=600,scrollbars=yes");
    }

    function addPemohon2(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showMaklumatIbuBapa", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=810,height=600,scrollbars=yes");
    }

    function addPemohon4(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showMaklumatAnak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");
    }


    function removePemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                self.refreshPageMaklumatPemohon();
            },'html');
        }
    }


    function removeLatarbelakangSuamiIsteri(val){
        alert(val);
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?deletePemohonHubungan&idHBGN='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

    }

    function removeLatarbelakangIbuBapa(val){
        alert(val);
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?deletePemohonHubungan&idHBGN='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function removeLatarbelakangAnak(val){
        alert(val);
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?deletePemohonHubungan&idHBGN='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }

    }





    function kemaskini(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showEditPemohon&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");
    }

      function kemaskiniIsteri(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showEditPemohon2&idHubungan="+i, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600 ,scrollbars=yes");
    }
   <%-- --%>
    function kemaskiniIbuBapa(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showEditPemohon3&idHubungan="+i, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600 ,scrollbars=yes");
    }

     function kemaskiniAnak(i){
        var e = $('.x'+i).val();

        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showEditPemohon4&idHubungan="+i, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600 ,scrollbars=yes");
    }

    function removePengarah(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?deletePihakPengarah&idPengarah='+val;
            $.get(url,function(data){$('#page_div').html(data);},'html');
        }
    }

    function addPemohonLembaga_Pengarah(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showMaklumatAhliLembaga", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }

    function kemaskinilembaga(i){
    <%-- var d = $('.x'+i).val();--%>
               window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showEditlembaga&idPengarah="+i, "eTanah",
               "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
           }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Pemohon</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon">

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
                    <display:column title="Kemaskini"><a href="#" onclick="kemaskini('${line_rowNum -1}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                           <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')">
                        </div>
                    </display:column>
                </display:table>
            </div>
            <c:if test="${line.pihak.idPihak eq null}">
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
            </div><br/>

            <%--<c:if test="${line.pihak.idPihak ne null }">--%>

            <c:if test="${line.pihak ne null}">
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'|| actionBean.pihak.jenisPengenalan.kod eq 'L'|| actionBean.pihak.jenisPengenalan.kod eq 'P'||actionBean.pihak.jenisPengenalan.kod eq 'T'||actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'N'||actionBean.pihak.jenisPengenalan.kod eq 'F'}">



            <pre>
                 <legend>Butir-butir Suami/Isteri</legend>

            </pre>
           <%-- </c:if>--%>
           <%--<c:if test="${isteri}">--%>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonHubunganList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idHubungan}"/>
                    </display:column>
                    <display:column property="nama" title="Nama Suami/Isteri"/>
                    <display:column property="noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Alamat Majikan" >${line.institusiAlamat1}
                        <c:if test="${line.institusiAlamat2 ne null}"> , </c:if>
                        ${line.institusiAlamat2}
                        <c:if test="${line.institusiAlamat3 ne null}"> , </c:if>
                        ${line.institusiAlamat3}
                        <c:if test="${line.institusiAlamat4 ne null}"> , </c:if>
                        ${line.institusiAlamat4}</display:column>
                    <display:column property="poskod" title="Poskod" />
                    <display:column property="negeri.nama" title="Negeri" />
                    <display:column title="Kemaskini"><a href="#" onclick="kemaskiniIsteri('${line.idHubungan}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removeLatarbelakangSuamiIsteri('${line.idHubungan}')">
                        </div>
                    </display:column>
                </display:table>

               <c:if test="${actionBean.pemohon.pihak.kodJantina eq '1'}">
                          <c:if test="${fn:length(actionBean.pemohonHubunganList) < 4}">
                        <tr><td>
                                <br>
                                <s:button name="" id="Tambah" value="Tambah " class="btn"  style="azimuth: left" onclick="addPemohon1();"/>
                            </td></tr>
                          </c:if>
                      </c:if>
                      <c:if test="${actionBean.pemohon.pihak.kodJantina eq '2'}">
                          <c:if test="${fn:length(actionBean.pemohonHubunganList) < 1}">
                        <tr><td>
                                <br>
                                <s:button name="" id="Tambah" value="Tambah " class="btn"  style="azimuth: left" onclick="addPemohon1();"/>
                            </td></tr>
                          </c:if>
                      </c:if>

            </div>
          <br/><%--</c:if>--%>


         <%--   <c:if test="${line.pihak.idPihak eq null}">
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addPemohon1();"/>&nbsp;
            </p>
            </c:if>--%>
           <%--        <br>
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
            </div>--%>


             <%--<c:if test="${isteri}">--%>
                 <pre>
                 <legend>Butir-butir IbuBapa</legend>

            </pre>



          <%-- </c:if>--%>
          <%--<c:if test="${ibu}">--%>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonHubunganList1}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idHubungan}"/>
                    </display:column>
                    <display:column property="nama" title="Nama"/>
                    <display:column property="noPengenalan" title="Nombor Pengenalan" /><s:hidden name="noPengenalan"/>
                    <display:column title="Alamat" >${line.alamat1}
                        <c:if test="${line.alamat2 ne null}"> , </c:if>
                        ${line.alamat2}
                        <c:if test="${line.alamat3 ne null}"> , </c:if>
                        ${line.alamat3}
                        <c:if test="${line.alamat4 ne null}"> , </c:if>
                        ${line.alamat4}</display:column>
                    <display:column property="poskod" title="Poskod" />
                    <display:column property="negeri.nama" title="Negeri" />
                    <display:column title="Kemaskini"><a href="#" onclick="kemaskiniIbuBapa('${line.idHubungan}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removeLatarbelakangIbuBapa('${line.idHubungan}')">
                        </div>
                    </display:column>
                </display:table>
            </div>
          <pre >

                                                                     <s:button name="" id="Tambah" value="Tambah" class="btn" onclick="addPemohon2();"/>

          </pre>
              <pre>
                 <legend>Butir-butir Anak</legend>

            </pre>
           <%-- </c:if>--%>

            <%--<c:if test="${anak}">--%>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonHubunganList2}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_pemohon">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <%--<s:hidden name="x" class="x${line_rowNum -1}" value="${line.idHubungan}"/>--%>
                    </display:column>
                    <display:column property="nama" title="Nama Anak"/>
                    <display:column property="kodJantina" title="Jantina" />
                   <display:column property="umur" title="Umur" />
                    <display:column property="institusi" title="NamaSekolah" />
                    <display:column title="Kemaskini"><a href="#" onclick="kemaskiniAnak('${line.idHubungan}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removeLatarbelakangAnak('${line.idHubungan}')">
                        </div>
                    </display:column>
                </display:table>

                    <br>

               <s:button name="" id="Tambah" value="Tambah" class="btn" onclick="addPemohon4();"/>

        </div>
         </c:if>
            <br/>


            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                    <legend>Butir-butir Ahli Lembaga Pengarah</legend>
                    <div class="content" align="center">

                        <display:table class="tablecloth" name="${actionBean.pihakPengarahList}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon">

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

           <%--  <c:if test="${actionBean.pihak.name ne null}">
            <legend>Butir-butir Anak</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama Anak Pemohon"/>
                    <display:column property="pihak.jantina" title="Jantina" />
                    <display:column property="permohonanPihak.umur" title="Umur" />
                    <display:column property="namaSekolah" title="Nama Sekolah" />
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
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addAnakPemohon();"/>&nbsp;
            </p>

            <br/><br/>
            </c:if>--%>



            <%--<p>
     <%--<c:if test="${actionBean.pihak.noPengenalan ne null}">
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="pihak.namaAhli" title="Nama Ahli"/>
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
                    <display:column property="pihak.jumSaham" title="Jumlah Saham"/>
                    <display:column title="Kemaskini"><a href="#" onclick="kemaskiniALPengarah('${line_rowNum -1}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removeLatarbelakangIbuBapa('${line.idPemohon}')">
                        </div>
                    </display:column>
                </display:table>
            </div></c:if>           <label>&nbsp;</label>
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addAhliLembaga();"/>&nbsp;
            </p>--%>
        </fieldset>
    </div>
</s:form>


