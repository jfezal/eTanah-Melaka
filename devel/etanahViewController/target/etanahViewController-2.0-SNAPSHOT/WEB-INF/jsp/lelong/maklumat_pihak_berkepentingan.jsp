<%-- 
    Document   : maklumat_pihak_berkepentingan
    Created on : Mar 3, 2010, 11:48:45 AM
    Author     : mazurahayati
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
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

    function popup(id){
        window.open("${pageContext.request.contextPath}/common/maklumat_hakmilik_single?popup&idSyarat="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function popupAlamat(val){
        window.open("${pageContext.request.contextPath}/lelong/pihak_berkepentingan?tambahAlamat&nama="+val, "eTanah",
        "status=0,toolbar=0,scrollbars=1,location=0,menubar=0,width=1000,height=1200");
    }
    
    function popupSuku(val){
        window.open("${pageContext.request.contextPath}/lelong/pihak_berkepentingan?tambahSuku&nama="+val, "eTanah",
        "status=0,toolbar=0,scrollbars=1,location=0,menubar=0,width=1000,height=1200");
    }
    
    function popupSukuview(val){
        window.open("${pageContext.request.contextPath}/lelong/pihak_berkepentingan?tambahSukuview&nama="+val, "eTanah",
        "status=0,toolbar=0,scrollbars=1,location=0,menubar=0,width=1000,height=1200");
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/lelong/pihak_berkepentingan?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function validate(){

        if($("#tujuanGadaian").val() == "null"){
            alert("Sila Pilih Jenis Gadaian");
            $('#tujuanGadaian').focus();
            return false;
        }
        return true;
    }


    function deleteId(val){
        if(confirm('Adakah anda pasti?')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/lelong/pihak_berkepentingan?deleteId&id='+ val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
            $.unblockUI();
        }
    }

    function saveID(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var len = $(".rowCount").length;
        var id = null;
        for (var i=0; i<=len; i++){
            if($("#idPerserahan"+i).is(':checked') == true){
                id = $("#idPerserahan"+i).val();
            }
        }
        if(id == null){
            alert("Sila Pilih No Perserahan Gadaian");
            return null;
        }
        var url = '${pageContext.request.contextPath}/lelong/pihak_berkepentingan?savePermohonanAtasPerserahan&idPerserahan='+ id;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        $.unblockUI();
    }
</script>

<s:form beanclass="etanah.view.stripes.lelong.PihakPentingActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <s:hidden name="pihak.idPihak"/>
    <s:hidden name="permohonanPihak.idPermohonanPihak"/>
    <s:hidden name="permohonan.idPermohonan"/>
    <c:if test="${actionBean.view eq false}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik
                </legend><br>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                                   requestURI="/lelong/maklumat_tanah" id="line">
                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                        <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.kategoriTanah.nama" title="Kategori Tanah" />
                    </display:table>
                </div><br>

                <%--untuk urusan selain PJTA--%>
                <c:if test="${actionBean.ppjp eq true}">
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listHakmilikPihakBerkepentingan}" cellpadding="0" cellspacing="0" requestURI="/lelong/maklumat_tanah" id="line2">
                            <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                            <display:column title="Nama" property="nama" style="text-transform:uppercase;">
                            </display:column>
                            <display:column title="Nombor Pengenalan" property="noPengenalan" style="text-transform:uppercase;"/>
                            <display:column title="ID Hakmilik" property="hakmilik.idHakmilik"/>
                            <display:column title="Bahagian yang diterima">
                                ${line2.syerPembilang}
                                /
                                ${line2.syerPenyebut}
                            </display:column>
                            <display:column property="jenis.nama" title="Status" style="text-transform:uppercase;"/>
                        </display:table>
                    </div>
                </c:if>



                <%--untuk urusan PJTA--%>
                <c:if test="${actionBean.ppjp eq false}">
                    <c:if test="${actionBean.viewnama eq false}">
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.listHakmilikPihakBerkepentingan}" cellpadding="0" cellspacing="0" requestURI="/lelong/maklumat_tanah" id="line2">
                                <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                                <display:column title="Nama" style="text-transform:uppercase;">
                                    <c:if test="${line2.jenis.kod eq 'PG'}"> ${line2.pihak.nama}</c:if>
                                    <c:if test="${line2.jenis.kod ne 'PG'}">
                                        <a href="#" onclick="popupSuku('${line2.pihak.idPihak}');return false;">${line2.pihak.nama}</a>
                                    </c:if>
                                </display:column>
                                <display:column title="Nombor Pengenalan" property="pihak.noPengenalan" style="text-transform:uppercase;"/>
                                <display:column title="ID Hakmilik" property="hakmilik.idHakmilik"/>
                                <display:column title="Bahagian yang diterima">
                                    ${line2.syerPembilang}
                                    /
                                    ${line2.syerPenyebut}
                                </display:column>
                                <display:column property="jenis.nama" title="Status" style="text-transform:uppercase;"/>
                            </display:table>
                        </div>
                    </c:if>
                    <c:if test="${actionBean.viewnama eq true}">
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.listHakmilikPihakBerkepentingan}" cellpadding="0" cellspacing="0" requestURI="/lelong/maklumat_tanah" id="line2">
                                <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                                <display:column title="Nama" style="text-transform:uppercase;">
                                    <c:if test="${line2.jenis.kod eq 'PG'}"> ${line2.pihak.nama}</c:if>
                                    <c:if test="${line2.jenis.kod ne 'PG'}">
                                        <a href="#" onclick="popupSukuview('${line2.pihak.idPihak}');return false;">${line2.pihak.nama}</a>
                                    </c:if>
                                </display:column>
                                <display:column title="Nombor Pengenalan" property="pihak.noPengenalan" style="text-transform:uppercase;"/>
                                <display:column title="ID Hakmilik" property="hakmilik.idHakmilik"/>
                                <display:column title="Bahagian yang diterima">
                                    ${line2.syerPembilang}
                                    /
                                    ${line2.syerPenyebut}
                                </display:column>
                                <display:column property="jenis.nama" title="Status" style="text-transform:uppercase;"/>
                            </display:table>
                        </div>
                    </c:if>

                </c:if>
                <br>

                <%--untuk urusan PPJP--%>
                <c:if test="${actionBean.tangguhbatale ne true}">

                    <div align="center">
                        <%--<c:if test="${actionBean.iDSerah eq true}">
                            <c:set value="0" var="count"/>
                            <display:table name="${actionBean.listHakmilikUrusan}" id="line1" class="tablecloth" requestURI="/lelong/maklumat_pemohon">
                                <display:column title="Bil">
                                    ${line1_rowNum}
                                </display:column>
                                <display:column property="idPerserahan" title="No.Perserahan Gadaian" class="rowCount" />
                                <display:column title="Tarikh Daftar" >
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line1.tarikhDaftar}"/>
                                </display:column>
                                <display:column title="ID Hakmilik" >
                                    ${actionBean.listidHakmilik[count]}
                                </display:column>
                                <c:set value="${count +1}" var="count"/>
                                <display:column  title="Pilih" >
                                    <div align="center">
                                        <s:checkbox name="idPerserahan${line1_rowNum}" id="idPerserahan${line1_rowNum - 1}"  class="checkbox" value="${line1.idPerserahan}"/>
                                    </div>
                                </display:column>
                            </display:table><br>

                            <p align="center" ><label></label>
                                <br>
                                <s:button name="savePermohonanAtasPerserahan" id="" value="Simpan" class="btn" onclick="saveID();"/>
                            </p><br>
                            checking if listNoPerserahan null
                            <c:if test="${fn:length(actionBean.listNoPerserahan)>0}">
                                <display:table name="${actionBean.listNoPerserahan}" id="line2" class="tablecloth" requestURI="/lelong/maklumat_pemohon">
                                    <display:column title="Bil">
                                        ${line2_rowNum}
                                    </display:column>
                                    <display:column property="urusan.idPerserahan" title="No.Perserahan Gadaian" />
                                    <display:column property="urusan.hakmilik.idHakmilik" title="IDHakmilik"/>
                                    <display:column title="Tarikh Daftar">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line2.urusan.tarikhDaftar}"/>
                                    </display:column>
                                    <display:column title="Jenis Gadaian" >
                                        ${actionBean.enkuiri.tujuanGadaian}
                                    </display:column>
                                    <c:if test="${line2.urusan.kodUrusan.kod eq 'GDPJ' || line2.urusan.kodUrusan.kod eq 'GDPJK'}">
                                        <display:column title="Tempoh Pajakan">
                                            ${actionBean.tempohGadaian}
                                        </display:column>
                                        <display:column title="Tarikh Tamat Pajakan">
                                            ${actionBean.tamatGadaian}
                                        </display:column>
                                    </c:if>
                                    <display:column title="">
                                        <a onclick="deleteId('${line2.idAtasUrusan}');return false;" onmouseover="this.style.cursor='pointer';" >
                                            <img alt='Klik Untuk Hapus' title="Klik Untuk Hapus" src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                 onmouseover="this.style.cursor='pointer';" onclick="" />
                                        </a>
                                    </display:column>
                                </display:table><br>
                            </c:if>
                        </c:if>--%>

                        <%--<c:if test="${actionBean.iDSerah eq false}">--%>
                        <c:if test="${fn:length(actionBean.listNoPerserahan)>0}">
                            <display:table name="${actionBean.listNoPerserahan}" id="line2" class="tablecloth" requestURI="/lelong/maklumat_pemohon">
                                <display:column title="Bil">
                                    ${line2_rowNum}
                                </display:column>
                                <display:column property="urusan.idPerserahan" title="No.Perserahan Gadaian" />
                                <display:column property="urusan.hakmilik.idHakmilik" title="IDHakmilik"/>
                                <display:column title="Tarikh Daftar">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line2.urusan.tarikhDaftar}"/>
                                </display:column>
                                <display:column title="Jenis Gadaian" >
                                    ${actionBean.enkuiri.tujuanGadaian}
                                </display:column>
                                <c:if test="${line2.urusan.kodUrusan.kod eq 'GDPJ' || line2.urusan.kodUrusan.kod eq 'GDPJK'}">
                                    <display:column title="Tempoh Pajakan">
                                        ${actionBean.tempohGadaian}
                                    </display:column>
                                    <display:column title="Tarikh Tamat Pajakan">
                                        ${actionBean.tamatGadaian}
                                    </display:column>
                                </c:if>
                            </display:table><br>
                        </c:if>
                        <%--</c:if>--%>
                    </div>
                </c:if>

                <%--untuk urusan tangguh/batal--%>
                <c:if test="${actionBean.tangguhbatale eq true}">
                    <div align="center">
                        <c:if test="${fn:length(actionBean.listNoPerserahan)>0}">
                            <display:table name="${actionBean.listNoPerserahan}" id="line2" class="tablecloth" requestURI="/lelong/maklumat_pemohon">
                                <display:column title="Bil">
                                    ${line2_rowNum}
                                </display:column>
                                <display:column property="urusan.idPerserahan" title="No.Perserahan Gadaian" />
                                <display:column property="urusan.hakmilik.idHakmilik" title="IDHakmilik"/>
                                <display:column title="Tarikh Daftar">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line2.urusan.tarikhDaftar}"/>
                                </display:column>
                                <display:column title="Jenis Gadaian" >
                                    ${actionBean.enkuiriSBlm.tujuanGadaian}
                                </display:column>
                                <c:if test="${line2.urusan.kodUrusan.kod eq 'GDPJ' || line2.urusan.kodUrusan.kod eq 'GDPJK'}">
                                    <display:column title="Tempoh Pajakan">
                                        ${actionBean.tempohGadaian}
                                    </display:column>
                                    <display:column title="Tarikh Tamat Pajakan">
                                        ${actionBean.tamatGadaian}
                                    </display:column>
                                </c:if>
                            </display:table><br>
                        </c:if>
                    </div>
                </c:if>
                <br>
            </fieldset>
        </div>
        <br>

        <div>
            <fieldset class="aras1">
                <legend>
                    Maklumat Penggadai/Pemegang Gadaian
                </legend> <br>
                <div class="content" align="center">
                    <c:set value="0" var="count"/>
                    <display:table name="${actionBean.senaraiPermohonanPihak3}" id="line1" class="tablecloth" requestURI="/lelong/maklumat_pemohon">
                        <display:column title="Bil">
                            ${line1_rowNum}
                        </display:column>
                        <display:column property="nama" title="Nama" style="text-transform:uppercase;" />
                        <display:column property="noPengenalan" title="No. Pengenalan" style="text-transform:uppercase;" />
                        <display:column title="ID Hakmilik">
                            ${actionBean.listIDHakmilik2[count]}
                        </display:column>
                        <c:set value="${count +1}" var="count"/>
                        <display:column title="Alamat" class="alamat">

                            <c:if test="${line1.jenis.kod ne 'PG'}">
                                <c:forEach items="${actionBean.listHakmilikPihakBerkepentingan}" var="line2">
                                    <c:if test="${line1.pihak.idPihak eq line2.pihak.idPihak}">
                                        <c:if test="${line2.alamat1 ne null}">
                                        <c:if test="${line2.alamat1 ne null}">Alamat Geran : </c:if>
                                        <font style="text-transform:uppercase;"> ${line2.alamat1}</font> <c:if test="${line2.alamat2 ne null}">,</c:if>
                                        <font style="text-transform:uppercase;"> ${line2.alamat2}</font> <c:if test="${line2.alamat3 ne null}">,</c:if>
                                        <font style="text-transform:uppercase;"> ${line2.alamat3}</font> <c:if test="${line2.alamat4 ne null}">,</c:if>
                                        <font style="text-transform:uppercase;"> ${line2.alamat4}</font> <c:if test="${line2.poskod ne null}">,</c:if>
                                        ${line2.poskod}<c:if test="${line2.negeri ne null}">,</c:if>
                                        <font style="text-transform:uppercase;"> ${line2.negeri.nama}</font>
					<br>
                                        </c:if>
                                    </c:if>

                                </c:forEach>

                            </c:if>
                            <c:if test="${line1.alamat.alamat1 ne null}">
                                <c:if test="${line1.alamat.alamat1 ne null}">Alamat 1 : </c:if>
                                <font style="text-transform:uppercase;"> ${line1.alamat.alamat1}</font> <c:if test="${line1.alamat.alamat2 ne null}">,</c:if>
                                <font style="text-transform:uppercase;"> ${line1.alamat.alamat2}</font> <c:if test="${line1.alamat.alamat3 ne null}">,</c:if>
                                <font style="text-transform:uppercase;"> ${line1.alamat.alamat3}</font> <c:if test="${line1.alamat.alamat4 ne null}">,</c:if>
                                <font style="text-transform:uppercase;"> ${line1.alamat.alamat4}</font> <c:if test="${line1.alamat.poskod ne null}">,</c:if>
                                ${line1.alamat.poskod}<c:if test="${line1.alamat.negeri ne null}">,</c:if>
                                <font style="text-transform:uppercase;"> ${line1.alamat.negeri.nama}</font>
                                <br>
                            </c:if>

                            <c:if test="${line1.alamatSurat.alamatSurat1 ne null}">
                                <c:if test="${line1.alamatSurat.alamatSurat1 ne null}">Alamat 2 : </c:if>
                                <font style="text-transform:uppercase;"> ${line1.alamatSurat.alamatSurat1}</font> <c:if test="${line1.alamatSurat.alamatSurat2 ne null}">,</c:if>
                                <font style="text-transform:uppercase;"> ${line1.alamatSurat.alamatSurat2}</font> <c:if test="${line1.alamatSurat.alamatSurat3 ne null}">,</c:if>
                                <font style="text-transform:uppercase;"> ${line1.alamatSurat.alamatSurat3}</font> <c:if test="${line1.alamatSurat.alamatSurat4 ne null}">,</c:if>
                                <font style="text-transform:uppercase;"> ${line1.alamatSurat.alamatSurat4}</font> <c:if test="${line1.alamatSurat.poskodSurat ne null}">,</c:if>
                                ${line1.alamatSurat.poskodSurat}<c:if test="${line1.alamatSurat.negeriSurat ne null}">,</c:if>
                                <font style="text-transform:uppercase;"> ${line1.alamatSurat.negeriSurat.nama}</font>
				<br>
                            </c:if>

                            <c:if test="${fn:length(line1.pihak.senaraiAlamatTamb)>0}">
                                <c:set value="1" var="count"/>
                                <c:forEach var="i" items="${line1.pihak.senaraiAlamatTamb}" >

                                    <%--<c:if test="${i.alamatKetiga1 ne null}"><br><br>Alamat 3 : </c:if>--%>
                                    <c:if test="${i.alamatKetiga1 ne null}"><br><br>Alamat Surat Menyurat 1 : </c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKetiga1}</font> <c:if test="${i.alamatKetiga2 ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKetiga2}</font> <c:if test="${i.alamatKetiga3 ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKetiga3}</font> <c:if test="${i.alamatKetiga4 ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKetiga4}</font> <c:if test="${i.alamatKetigaPoskod ne null}">,</c:if>
                                    ${i.alamatKetigaPoskod}<c:if test="${i.alamatKetigaNegeri ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKetigaNegeri.nama}</font>
                                    <br>

                                    <%--<c:if test="${i.alamatKeempat1 ne null}"><br><br>Alamat 4 : </c:if>--%>
                                    <c:if test="${i.alamatKeempat1 ne null}"><br><br>Alamat Surat Menyurat 2 : </c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKeempat1}</font> <c:if test="${i.alamatKeempat2 ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKeempat2}</font> <c:if test="${i.alamatKeempat3 ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKeempat3}</font> <c:if test="${i.alamatKeempat4 ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKeempat4}</font> <c:if test="${i.alamatKeempatPoskod ne null}">,</c:if>
                                    ${i.alamatKeempatPoskod}<c:if test="${i.alamatKeempatNegeri ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKeempatNegeri.nama}</font>
                                    <br>

                                    <%--<c:if test="${i.alamatKelima1 ne null}"><br><br>Alamat 5 : </c:if>--%>
                                    <c:if test="${i.alamatKelima1 ne null}"><br><br>Alamat Surat Menyurat 3 : </c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKelima1}</font> <c:if test="${i.alamatKelima2 ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKelima2}</font> <c:if test="${i.alamatKelima3 ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKelima3}</font> <c:if test="${i.alamatKelima4 ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKelima4}</font> <c:if test="${i.alamatKelimaPoskod ne null}">,</c:if>
                                    ${i.alamatKelimaPoskod}<c:if test="${i.alamatKelimaNegeri ne null}">,</c:if>
                                    <font style="text-transform:uppercase;"> ${i.alamatKelimaNegeri.nama}</font>
                                    <br>
                                    <c:set value="${count +1}" var="count"/>
                                </c:forEach>
                            </c:if>


                            <div align="right">
                                <a id="" onclick="popupAlamat('${line1.pihak.idPihak}');return false;" onmouseover="this.style.cursor='pointer';">
                                    <img alt="Sila Klik Untuk Masukkan Alamat Pemilik/Penggadai" src='${pageContext.request.contextPath}/pub/images/edit.gif' />

                                </a>
                            </div>
                        </display:column>
                        <display:column title="Status" class="${line1_rowNum}">
                            <c:if test="${line1.jenis.kod eq 'PG'}">
                                <font style="text-transform:uppercase;">${line1.jenis.nama}</font> 
                            </c:if>
                            <c:if test="${line1.jenis.kod ne 'PG'}">
                                PENGGADAI
                            </c:if>
                        </display:column>
                    </display:table><br>
                </div>
            </fieldset>
        </div>

        <%--<<div>
            <fieldset class="aras1">
                <legend>
                    Maklumat Penggadai/Pemegang Gadaian
                </legend> <br>
                <div class="content" align="center">
        <c:set value="0" var="count"/>
        <display:table name="${actionBean.senaraiPermohonanPihak3}" id="line1" class="tablecloth" requestURI="/lelong/maklumat_pemohon">
            <display:column title="Bil">
                ${line1_rowNum}
            </display:column>
            <display:column property="pihak.nama" title="Nama" style="text-transform:uppercase;" />
            <display:column property="pihak.noPengenalan" title="No. Pengenalan" style="text-transform:uppercase;" />
            <display:column title="ID Hakmilik">
                ${actionBean.listIDHakmilik2[count]}
            </display:column>
            <c:set value="${count +1}" var="count"/>
            <display:column title="Alamat" class="alamat">

                <c:if test="${line1.pihak.alamat1 ne null}">Alamat 1 : </c:if>
                <font style="text-transform:uppercase;"> ${line1.pihak.alamat1}</font> <c:if test="${line1.pihak.alamat2 ne null}">,</c:if>
                <font style="text-transform:uppercase;"> ${line1.pihak.alamat2}</font> <c:if test="${line1.pihak.alamat3 ne null}">,</c:if>
                <font style="text-transform:uppercase;"> ${line1.pihak.alamat3}</font> <c:if test="${line1.pihak.alamat4 ne null}">,</c:if>
                <font style="text-transform:uppercase;"> ${line1.pihak.alamat4}</font> <c:if test="${line1.pihak.poskod ne null}">,</c:if>
                ${line1.pihak.poskod}<c:if test="${line1.pihak.negeri ne null}">,</c:if>
                <font style="text-transform:uppercase;"> ${line1.pihak.negeri.nama}</font><br><br>
                <c:if test="${line1.pihak.suratAlamat1 ne null}">Alamat 2 : </c:if>
                <font style="text-transform:uppercase;"> ${line1.pihak.suratAlamat1}</font> <c:if test="${line1.pihak.suratAlamat2 ne null}">,</c:if>
                <font style="text-transform:uppercase;"> ${line1.pihak.suratAlamat2}</font> <c:if test="${line1.pihak.suratAlamat3 ne null}">,</c:if>
                <font style="text-transform:uppercase;"> ${line1.pihak.suratAlamat3}</font> <c:if test="${line1.pihak.suratAlamat4 ne null}">,</c:if>
                <font style="text-transform:uppercase;"> ${line1.pihak.suratAlamat4}</font> <c:if test="${line1.pihak.suratPoskod ne null}">,</c:if>
                ${line1.pihak.suratPoskod}<c:if test="${line1.pihak.suratNegeri ne null}">,</c:if>
                <font style="text-transform:uppercase;"> ${line1.pihak.suratNegeri.nama}</font>

                <c:if test="${fn:length(line1.pihak.senaraiAlamatTamb)>0}">
                    <c:forEach var="i" items="${line1.pihak.senaraiAlamatTamb}" >

                        <c:if test="${i.alamatKetiga1 ne null}"><br><br>Alamat 3 : </c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKetiga1}</font> <c:if test="${i.alamatKetiga2 ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKetiga2}</font> <c:if test="${i.alamatKetiga3 ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKetiga3}</font> <c:if test="${i.alamatKetiga4 ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKetiga4}</font> <c:if test="${i.alamatKetigaPoskod ne null}">,</c:if>
                        ${i.alamatKetigaPoskod}<c:if test="${i.alamatKetigaNegeri ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKetigaNegeri.nama}</font>

                        <c:if test="${i.alamatKeempat1 ne null}"><br><br>Alamat 4 : </c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKeempat1}</font> <c:if test="${i.alamatKeempat2 ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKeempat2}</font> <c:if test="${i.alamatKeempat3 ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKeempat3}</font> <c:if test="${i.alamatKeempat4 ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKeempat4}</font> <c:if test="${i.alamatKeempatPoskod ne null}">,</c:if>
                        ${i.alamatKeempatPoskod}<c:if test="${i.alamatKeempatNegeri ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKeempatNegeri.nama}</font>

                        <c:if test="${i.alamatKelima1 ne null}"><br><br>Alamat 5 : </c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKelima1}</font> <c:if test="${i.alamatKelima2 ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKelima2}</font> <c:if test="${i.alamatKelima3 ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKelima3}</font> <c:if test="${i.alamatKelima4 ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKelima4}</font> <c:if test="${i.alamatKelimaPoskod ne null}">,</c:if>
                        ${i.alamatKelimaPoskod}<c:if test="${i.alamatKelimaNegeri ne null}">,</c:if>
                        <font style="text-transform:uppercase;"> ${i.alamatKelimaNegeri.nama}</font>

                    </c:forEach>
                </c:if>


                <div align="right">
                    <a id="" onclick="popupAlamat('${line1.pihak.idPihak}');return false;" onmouseover="this.style.cursor='pointer';">
                        <img alt="Sila Klik Untuk Masukkan Alamat Pemilik/Penggadai" src='${pageContext.request.contextPath}/pub/images/edit.gif' />

                    </a>
                </div>
            </display:column>
            <display:column title="Status" class="${line1_rowNum}">
                <c:if test="${line1.jenis.kod eq 'PG'}">
                    <font style="text-transform:uppercase;">${line1.jenis.nama}</font> 
                </c:if>
                <c:if test="${line1.jenis.kod ne 'PG'}">
                    PENGGADAI
                </c:if>
            </display:column>
        </display:table><br>
    </div>
</fieldset>
</div>>--%>
    </c:if>
</s:form>

