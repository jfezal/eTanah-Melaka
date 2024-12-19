<%-- 
    Document   : dev_senarai_pemohon
    Created on : Jun 28, 2010, 11:28:51 AM
    Author     : nursyazwani
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('.empty').remove();

        var len2 = $(".alamat").length;

        for (var i = 0; i < len2; i++) {
            var d = $('.x' + i).val();

            $('.nama' + i).bind('click', d, function() {
                window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?showEditPemohon&idPihak=" + d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
            });
        }
        $('#page_effect').fadeIn(5000);
    });

    function addNewPemohon() {
        alert
        window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?pemohonPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=700,scrollbars=yes");
    }

    function remove(val) {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/common/mohon_pihak?delete&idPermohonanPihak=' + val;
            $.get(url,
            function(data) {
                $('#page_div').html(data);
            }, 'html');
        }
    }

    function removePemohon(val) {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?deletePemohon&idPemohon=' + val;
            $.get(url,
            function(data) {
                $('#page_div').html(data);
            }, 'html');
        }
    }
    
    function removeChanges(val) {
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer) {
            var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?deleteChanges&idKkini=' + val;
            $.get(url,
            function(data) {
                $('#page_div').html(data);
                self.opener.refreshPageUlasanJabatanTeknikal();
            });
        }
    }


    function pemohoneqPenyerah(val,radioButton) {
        if(radioButton == "Y"){
            alert("Pemohon Dan Penyerah Adalah Orang Yang Sama !!!");
        }
    
        var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?simpanPenyeraheqPemohon&idPermohonan=' + val +'&radio1='+radioButton;
        $.get(url,
        function(data) {
            $('#page_div').html(data);
        }, 'html');
            
    }
    
    function pemohonUtama(val,radioButton,idPihak,nama) {
        if(radioButton == "Y"){
            alert("Pemohon Utama ialah " + nama + " !!!");
        }
    
        var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?simpanPemohonUtama&idPermohonan=' + val 
            +'&radio1='+radioButton+'&idPihak='+idPihak;
        $.get(url,
        function(data) {
            $('#page_div').html(data);
        }, 'html');
            
    }
    

    function addPemohon() {
        var len = $('.nama').length;
        for (var i = 1; i <= len; i++) {
            var ckd = $('#chkbox' + i).is(":checked");
            if (ckd) {
                var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?simpanPemohon&idPihak=' + $('#chkbox' + i).val();
                $.get(url,
                function(data) {
                    $('#page_div').html(data);
                });
            }
        }
    }


    function refreshPagePemohon() {
    <%--alert("refreshPagePemohon");--%>
            var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?getSenaraiPemohon';
            $.get(url,
            function(data) {
                $('#page_div').html(data);
            }, 'html');
        }


        function semakSyer(f) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?semakSyer', q,
            function(data) {
                if (data != '') {
                    alert(data);
                }
            }, 'html');
        }

        function dopopup(i, kod, idPihak) {
            if (kod == "TN") {
                var url = "showEditNamaPemohon";
            }
            else if (kod == "TA") {
                var url = "showEditAlamatPemohon";
            } else {
                var url = "showEditPemohon";
            }
    <%--var d = $('.x'+i).val();--%>            
            window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?" + url + "&idPihak=" + idPihak, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1100px,height=600px");
        }

</script>
<div class="subtitle displaytag" id="page_effect" style="display:none;">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.pembangunan.PihakBerkepentinganActionBean">
        <fieldset class="aras1" style="display: none">
            <legend>Makluman :</legend>
            <div class="content" align="center">
                <fmt:message key="converter.number.invalidNumber"/>
            </div>
        </fieldset>

        <c:if test="${edit}">
            <div align ="center">
                <td><legend><b>Pemohon Dan Penyerah Adalah Orang Yang Sama ?  </b></legend></td>
                <td><s:radio name="radio1" value="Y" onclick="pemohoneqPenyerah('${actionBean.idPermohonan}','Y')"/> Ya &nbsp;
                    <s:radio name="radio1" value="T" onclick="pemohoneqPenyerah('${actionBean.idPermohonan}','T')"/>Tidak                   
                </td>

                <br><br>
                <td>
                    <%--<s:submit class="btn" value="simpan" name="simpanPenyeraheqPemohon" /> --%>
                </td>
            </div>
        </c:if>

        <c:if test="${!edit}">
            <div align ="center">
                <c:if test = "${actionBean.radio1 != null}">
                    <c:if test = "${actionBean.radio1 == 'Y'}">
                        <td><legend><b>Pemohon Dan Penyerah Adalah Orang Yang Sama : Ya </b></legend></td>  
                    </c:if>
                    <c:if test = "${actionBean.radio1 == 'T'}">
                        <td><legend><b>Pemohon Dan Penyerah Adalah Orang Yang Sama : Tidak </b></legend></td>  
                    </c:if>
                </c:if>
                <c:if test = "${actionBean.radio1 == null}">
                    <td><legend><b>Pemohon Dan Penyerah Adalah Orang Yang Sama ? : - </b></legend></td>  
                </c:if>
                <br><br>
                <c:if test = "${actionBean.radio2 != null}">
                    <td><legend><b>Pemohon Utama : ${actionBean.radio2} </b></legend></td> 
                </c:if>

            </div>
        </c:if>

        <c:if test="${(actionBean.p.kodUrusan.kod ne 'RPS') && (actionBean.p.kodUrusan.kod ne 'RPP') && (actionBean.p.kodUrusan.kod ne 'RLTB') }">
            <c:if test="${fn:length(actionBean.pemohonListIndividu) gt 0}">
                <fieldset class="aras1">
                    <legend>
                        Senarai Pemohon Individu
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.pemohonListIndividu}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                            <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                            </display:column>
                            <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>getJenisPengenalan().getNama()
                            <display:column property="pihak.jenisPengenalan.nama" title="Jenis Pengenalan" style="vertical-align:baseline"/>
                            <display:column property="pihak.noPengenalan" title="No. KP/Syarikat" style="vertical-align:baseline"/>
                            <%--<display:column title="Jantina" style="vertical-align:baseline">
                            <c:if test="${line.pihak.kodJantina eq '0' || line.pihak.kodJantina eq null || line.pihak.kodJantina eq ''}">Tidak Berkenaan</c:if>
                            <c:if test="${line.pihak.kodJantina eq '1'}">Lelaki</c:if>
                            <c:if test="${line.pihak.kodJantina eq '2'}">Perempuan</c:if>
                            <c:if test="${line.pihak.kodJantina eq '3'}">Tidak Dikenalpasti</c:if>
                            </display:column>--%>
                            <display:column property="pihak.bangsa.nama" title="Bangsa" style="vertical-align:baseline"/>
                            <display:column title="Alamat" style="vertical-align:baseline">${line.pihak.alamat1}
                                <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                                ${line.pihak.alamat2}
                                <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                                ${line.pihak.alamat3}
                                <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                                ${line.pihak.alamat4}</display:column>
                            <display:column property="pihak.poskod" title="Poskod" style="vertical-align:baseline"/>
                            <display:column property="pihak.negeri.nama" title="Negeri" style="vertical-align:baseline"/>
                            <display:column property="pihak.noTelefon1" title="No. Telefon 1" style="vertical-align:baseline"/>
                            <display:column property="pihak.noTelefon2" title="No. Telefon 2" style="vertical-align:baseline"/>
                            <c:if test="${edit}">
                                <display:column title="Kemaskini" style="vertical-align:baseline">
                                    <div align="center">
                                        <%--<a href="#" src='${pageContext.request.contextPath}/images/edit.gif' onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;">Kemaskini</a>--%>
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                             id='rem_${line_rowNum}' onclick="dopopup('${line_rowNum -1}', '${actionBean.p.kodUrusan.kod}', '${line.pihak.idPihak}');
                                                 return false;">
                                    </div>
                                </display:column>

                                <display:column title="Hapus" style="vertical-align:baseline">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')">
                                    </div>
                                </display:column>
                            </c:if>
                        </display:table>
                    </div>
                </fieldset>
            </c:if>
        </c:if>
        <c:if test="${fn:length(actionBean.pemohonListCompany) gt 0}">
            <c:if test="${(actionBean.p.kodUrusan.kod ne 'RPS') && (actionBean.p.kodUrusan.kod ne 'RPP') && (actionBean.p.kodUrusan.kod ne 'RLTB') }">
                <fieldset class="aras1">
                    <legend>
                        Senarai Pemohon Syarikat 
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.pemohonListCompany}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                            <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                            </display:column>
                            <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>getJenisPengenalan().getNama()
                            <display:column property="pihak.jenisPengenalan.nama" title="Jenis Pengenalan" style="vertical-align:baseline"/>
                            <display:column property="pihak.noPengenalan" title="No. KP/Syarikat" style="vertical-align:baseline"/>
                            <%--<display:column title="Jantina" style="vertical-align:baseline">
                            <c:if test="${line.pihak.kodJantina eq '0' || line.pihak.kodJantina eq null || line.pihak.kodJantina eq ''}">Tidak Berkenaan</c:if>
                            <c:if test="${line.pihak.kodJantina eq '1'}">Lelaki</c:if>
                            <c:if test="${line.pihak.kodJantina eq '2'}">Perempuan</c:if>
                            <c:if test="${line.pihak.kodJantina eq '3'}">Tidak Dikenalpasti</c:if>
                            </display:column>--%>
                            <display:column property="pihak.bangsa.nama" title="Bangsa" style="vertical-align:baseline"/>
                            <display:column title="Alamat" style="vertical-align:baseline">${line.pihak.alamat1}
                                <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                                ${line.pihak.alamat2}
                                <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                                ${line.pihak.alamat3}
                                <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                                ${line.pihak.alamat4}</display:column>
                            <display:column property="pihak.poskod" title="Poskod" style="vertical-align:baseline"/>
                            <display:column property="pihak.negeri.nama" title="Negeri" style="vertical-align:baseline"/>
                            <display:column title="Ahli Lembaga Pengarah" style="vertical-align:baseline">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${line.pihak.senaraiPengarah}" var="pengarah">
                                    <c:if test="${pengarah.nama ne null}">
                                        <c:out value="${count}"/>) &nbsp;
                                        <c:out value="${pengarah.nama}"/><br>
                                    </c:if>
                                    <c:if test="${pengarah.nama eq null}">
                                        <c:out value=""/>- &nbsp;
                                    </c:if>
                                    <c:set value="${count + 1}" var="count"/>
                                </c:forEach>
                            </display:column>
                            <%--Newly added--%>
                            <display:column property="pihak.modalBerbayar" title="Modal Berbayar" style="vertical-align:baseline"/>
                            <display:column property="pihak.modalDibenar" title="Modal Dibenarkan" style="vertical-align:baseline"/>
                            <display:column title="Tarikh Daftar Syarikat">
                                <fmt:formatDate value="${line.pihak.tarikhLahir}" pattern="dd/MM/yyyy"/>
                            </display:column>

                            <display:column property="pihak.noTelefon1" title="No. Telefon 1" style="vertical-align:baseline"/>
                            <display:column property="pihak.noTelefon2" title="No. Telefon 2" style="vertical-align:baseline"/>
                            <c:if test="${edit}">
                                <display:column title="Kemaskini" style="vertical-align:baseline">
                                    <div align="center">
                                        <%--<a href="#" src='${pageContext.request.contextPath}/images/edit.gif' onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;">Kemaskini</a>--%>
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="dopopup('${line_rowNum -1}', '${actionBean.p.kodUrusan.kod}', '${line.pihak.idPihak}');
                                                 return false;">
                                    </div>
                                </display:column>

                                <display:column title="Hapus" style="vertical-align:baseline">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePemohon('${line.idPemohon}')">
                                    </div>
                                </display:column>
                            </c:if>
                        </display:table>
                    </div>
                </fieldset>
            </c:if>
        </c:if>

        <p align="center">


            <c:if test="${actionBean.p.kodUrusan.kod eq 'RPS' || actionBean.p.kodUrusan.kod eq 'RPP'|| (actionBean.p.kodUrusan.kod eq 'RLTB')}">
            <fieldset class="aras1">
                <legend>
                    Senarai Pemohon  
                </legend>
                <div class="content" align="center">
                    <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiKeempunyaan}"
                                   cellpadding="0" cellspacing="0" id="linePemilik">      
                        <display:column title="Bil" sortable="true" style="width:40;">${linePemilik_rowNum}</display:column>
                        <display:column  title="Nama" class="nama">
                            <a href="#" onclick="viewPihak('${linePemilik.pihak.idPihak}', 'tuanTanah');
                                return false;"> 
                                <font style="text-transform:uppercase;">${linePemilik.pihak.nama}</font></a>
                            </display:column>
                            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                            <display:column title="Syer Dimiliki" >
                            <div align="center">
                                ${linePemilik.syerPembilang}/${linePemilik.syerPenyebut}
                            </div>
                        </display:column>
                        <display:column title="Jenis Pihak"><font style="text-transform:uppercase;">${linePemilik.jenis.nama} </font></display:column>

                    </display:table>
                </div>
            </fieldset>
        </c:if>

        <c:if test="${edit}">
            <fieldset class="aras1">
                <legend>
                    Pemohon Utama (Untuk Kegunaan Surat) 
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.allpemohonList}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                        <display:column title="Pilihan" sortable="true" style="vertical-align:baseline">
                            <div align="center"><s:radio name="radio2" value="${line.nama}" onclick="pemohonUtama('${actionBean.idPermohonan}','Y','${line.pihak.idPihak}','${line.pihak.nama}')"/></div>
                        </display:column>
                        <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>
                        <display:column title="Alamat" style="vertical-align:baseline">${line.pihak.alamat1}
                            <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                            ${line.pihak.alamat2}
                            <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                            ${line.pihak.alamat3}
                            <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                            ${line.pihak.alamat4}</display:column>
                        <display:column property="pihak.poskod" title="Poskod" style="vertical-align:baseline"/>
                        <display:column property="pihak.negeri.nama" title="Negeri" style="vertical-align:baseline"/>
                    </display:table>
                </div>
            </fieldset>
        </c:if>

    </p>

    <br/><br>
    <c:if test="${edit}">
        <p>
            <label>&nbsp;</label>
            <s:button class="btn" value="Tambah" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
        </p>
    </c:if>
</s:form>
</div>