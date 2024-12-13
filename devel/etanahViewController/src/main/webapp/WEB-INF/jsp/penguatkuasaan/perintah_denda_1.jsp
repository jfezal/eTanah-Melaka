<%-- 
    Document   : perintah_denda
    Created on : Oct 17, 2011, 1:46:46 PM
    Author     : latifah.iskak

--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        var amount = $('#amaun').val();
        document.getElementById('amaun').value = CurrencyFormatted(amount);
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

    });

    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_perintah_denda?popupTambah", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=950,height=800");
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_perintah_denda?refreshPage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function removeSingle(idKompaun,noRuj)
    {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_perintah_denda?delete&idKompaun='+idKompaun+'&noRujukan='+noRuj;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
        self.opener.refreshPage2();
    }

    function validate(){
        if($('#tempohDenda').val() == "")
        {
            alert("Sila masukkan tempoh (hari)");
            $('#tempohDenda').focus();
            return false;
        }

        if($('#amaun').val() == "")
        {
            alert("Sila masukkan amaun denda");
            $('#amaun').focus();
            return false;
        }

        if($('#amaun').val() =="0.00")
        {
            alert("Sila masukkan amaun denda");
            $('#amaun').focus();
            return false;
        }
        return true;
    }

    <%--    function updateTotal(amount){
            var amaun = $('#amaun').val();
            amaun = parseFloat(amaun).toFixed(2);
            if(amaun <500){
                alert("Sila masukkan jumlah denda melebihi atau sama dengan RM 500");
                $('#amaun').focus();
                document.getElementById("amaun").value="0.00";
                document.getElementById('amaun').value = CurrencyFormatted(amount);
            }
            document.getElementById('amaun').value = CurrencyFormatted(amount);


    }

    function updateTotalDendaTambahan(amount){

        var amaun = $('#amaun').val();
        amaun = parseFloat(amaun).toFixed(2);
        if(amaun <100){
            alert("Sila masukkan jumlah denda melebihi atau sama dengan RM 100");
            $('#amaun').focus();
            document.getElementById("amaun").value="0.00";
            document.getElementById('amaun').value = CurrencyFormatted(amount);
        }
        document.getElementById('amaun').value = CurrencyFormatted(amount);
        

    }--%>

        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

        function test(f) {
            $(f).clearForm();
        }



        function removeNonNumeric( strString )
        {
            var strValidCharacters = "1234567890";
            var strReturn = "";
            var strBuffer = "";
            var intIndex = 0;
            // Loop through the string
            for( intIndex = 0; intIndex < strString.length; intIndex++ )
            {
                strBuffer = strString.substr( intIndex, 1 );
                // Is this a number
                if( strValidCharacters.indexOf( strBuffer ) > -1 )
                {
                    strReturn += strBuffer;
                }
            }
            return strReturn;

        }

        function calculateFraction(){
            var amaun = $('#amaun').val();
            amaun = parseFloat(amaun).toFixed(2);

            var bil =  ${fn:length(actionBean.hakMilikPihakBerkepentinganList)};

            for (var i = 0; i < bil; i++){
                var syer = document.getElementById('syer'+i).value;
                alert(syer);
            
            }
        }

        function returnCurrency(amount){
            var amaun = $('#amaun').val();
            document.getElementById('amaun').value = CurrencyFormatted(amount);
            if(amaun <500){
                alert("Sila masukkan jumlah denda melebihi atau sama dengan RM 500");
                $('#amaun').focus();
            
            }
        }


        function returnCurrency2(amount){
            var amaun = $('#amaun').val();
            document.getElementById('amaun').value = CurrencyFormatted(amount);
            if(amaun <100){
                alert("Sila masukkan jumlah denda melebihi atau sama dengan RM 100");
                $('#amaun').focus();
            
            }
        }
        
        function autoFloatRemedi(amount){
            document.getElementById('amaun').value = CurrencyFormatted(amount);
        }

        function CurrencyFormatted(amount)
        {
            var i = parseFloat(amount);
            if(isNaN(i)) { i = 0.00; }
            var minus = '';
            if(i < 0) { minus = '-'; }
            i = Math.abs(i);
            i = parseInt((i + .005) * 100);
            i = i / 100;
            s = new String(i);
            if(s.indexOf('.') < 0) { s += '.00'; }
            if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
            s = minus + s;
            return s;
        }

</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatPerintahDendaActionBean" name="form1">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:errors/>
    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${edit}">
                <legend>
                    Maklumat Perintah Denda
                    <c:if test="${fn:length(actionBean.hakMilikPihakBerkepentinganList) eq 0}">
                        <div class="instr-fieldset">
                            <font color="red">PERINGATAN:</font>Tiada maklumat pihak berkepentingan.
                        </div>
                    </c:if>
                </legend>
                <div class="subtitle">
                    <fieldset class="aras1">
                        <c:if test="${fn:length(actionBean.hakmilikPermohonanList) ne 0}">
                            <p>
                                <label>Id Hakmilik : </label>
                                ${actionBean.hakmilik.idHakmilik}&nbsp;
                            </p>
                            <p>
                                <label>No Lot : </label>
                                ${actionBean.hakmilik.lot.nama}  ${actionBean.hakmilik.noLot}&nbsp;
                            </p>
                        </c:if>

                        <c:if test="${actionBean.stageId eq 'kpsn_pemantauan1' || actionBean.stageId eq 'sedia_denda_tambahan'}">
                            <%-- stage id for MLK : sedia_denda_tambahan
                                 stage id for NS : kpsn_pemantauan1--%>
                            <p>
                                <label><em>*</em>Nilai Denda Harian (RM) :</label>
                                <s:text id="amaun" name="amaun" size="15" class="number" formatPattern="0.00" maxlength="10" onkeyup="validateNumber(this,this.value);" onblur="returnCurrency2(this.value);"/>&nbsp;
                            </p>

                        </c:if>

                        <c:if test="${actionBean.stageId eq 'bukti_penyampaian_denda' || actionBean.stageId eq 'sedia_perintah_denda'}">
                            <%-- stage id for MLK : sedia_perintah_denda
                                stage id for NS : perakuan_srt_denda--%>
                            <%-- stage id for MLK : sedia_perintah_denda
                              stage id for NS : bukti_penyampaian_denda  updated (22/5/12)--%>
                            <p>
                                <label><em>*</em>Tempoh (Hari) :</label>
                                <s:text name="tempohDenda" size="15" onkeyup="validateNumber(this,this.value);" maxlength="2" id="tempohDenda"/>&nbsp;
                            </p>
                            <p>
                                <label><em>*</em>Jumlah Denda (RM) :</label>
                                <s:text id="amaun" name="amaun" size="15" class="number" formatPattern="0.00" maxlength="10" onkeyup="validateNumber(this,this.value);" onblur="returnCurrency(this.value);"/>&nbsp;
                            </p>
                        </c:if>




                    </fieldset>
                </div>
                <div class="content" align="center">
                    <c:if test="${fn:length(actionBean.hakmilikPermohonanList) ne 0}">
                        <display:table name="${actionBean.hakMilikPihakBerkepentinganList}" id="line1" class="tablecloth">
                            <display:column title="Bil">
                                ${line1_rowNum}
                            </display:column>
                            <display:column property="pihak.nama" title="Nama" />
                            <display:column property="pihak.noPengenalan" title="No. Pengenalan" />
                            <display:column title="Alamat" class="alamat">
                                ${line1.pihak.alamat1}<c:if test="${line1.pihak.alamat2 ne null}">,</c:if>
                                ${line1.pihak.alamat2}<c:if test="${line1.pihak.alamat3 ne null}">,</c:if>
                                ${line1.pihak.alamat3}<c:if test="${line1.pihak.alamat4 ne null}">,</c:if>
                                ${line1.pihak.alamat4}<c:if test="${line1.pihak.poskod ne null}">,</c:if>
                                ${line1.pihak.poskod}<c:if test="${line1.pihak.negeri.kod ne null}">,</c:if>
                                ${line1.pihak.negeri.nama}
                            </display:column>
                            <display:column property="pihak.noTelefon1" title="No. Telefon" />
                            <display:column property="pihak.email" title="Email" />
                            <display:column property="jenis.nama" title="Status" class="${line1_rowNum}"/>
                            <display:column title="Syer yang dimiliki">
                                ${line1.syerPembilang}/${line1.syerPenyebut}
                                <input type="hidden" name="syerPembilang${line1_rowNum-1}" value="${line1.syerPembilang}" id="syerPembilang${line1_rowNum-1}" />
                                <input type="hidden" name="syerPenyebut${line1_rowNum-1}" value="${line1.syerPenyebut}" id="syerPenyebut${line1_rowNum-1}" />
                            </display:column>
                        </display:table>
                    </c:if>

                    <c:if test="${fn:length(actionBean.senaraiTanahMilik) ne 0}">
                        <display:table  name="${actionBean.senaraiTanahMilik}" id="line" class="tablecloth">
                            <display:column title="Nombor Lot/PT" >
                                <c:if test="${line.noLot ne null}"> ${line.noLot}&nbsp; </c:if>
                                <c:if test="${line.noLot eq null}"> Tiada Data </c:if>
                            </display:column>

                            <display:column title="Kod Lot" >
                                <c:if test="${line.lot.nama ne null}"> ${line.lot.nama}&nbsp; </c:if>
                                <c:if test="${line.lot.nama eq null}"> Tiada Data </c:if>
                            </display:column>

                            <display:column title="Kategori Tanah" >
                                <c:if test="${line.kategoriTanahBaru.nama ne null}"> ${line.kategoriTanahBaru.nama}&nbsp; </c:if>
                                <c:if test="${line.kategoriTanahBaru.nama eq null}"> Tiada Data </c:if>
                            </display:column>

                            <display:column title="Catatan">
                                <c:if test="${line.catatan ne null}"> ${line.catatan}&nbsp; </c:if>
                                <c:if test="${line.catatan eq null}"> Tiada Data </c:if>
                            </display:column>
                        </display:table>
                    </c:if>

                    <br>
                    <c:if test="${fn:length(actionBean.hakMilikPihakBerkepentinganList) eq 0}">
                        <s:button name="simpanDenda" id="save" value="Simpan" disabled="true" class="disablebtn" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                    </c:if>
                    <c:if test="${fn:length(actionBean.hakMilikPihakBerkepentinganList) ne 0}">
                        <s:button name="simpanDenda" id="save" value="Simpan" class="btn" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                    </c:if>
                    <c:if test="${fn:length(actionBean.senaraiTanahMilik) ne 0}">
                        <s:button name="simpanDendaTanahMilik" id="save" value="Simpan" class="btn" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${view}">
                <legend>
                    Maklumat Perintah Denda
                </legend>
                <div class="subtitle">
                    <fieldset class="aras1">
                        <p>
                            <label>Id Hakmilik : </label>
                            ${actionBean.hakmilik.idHakmilik}&nbsp;
                        </p>
                        <p>
                            <label>No Lot : </label>
                            ${actionBean.hakmilik.lot.nama}  ${actionBean.hakmilik.noLot}&nbsp;
                        </p>
                        <p>
                            <label>Tempoh (Hari) :</label>
                            ${actionBean.tempohDenda}&nbsp;
                        </p>
                        <p>
                            <label>Jumlah Denda (RM) :</label>
                            <fmt:formatNumber pattern="#,##0.00" value="${actionBean.amaun}"/>
                            <%--${actionBean.amaun}&nbsp;--%>
                        </p>

                    </fieldset>
                </div>
                <div class="content" align="center">
                    <display:table name="${actionBean.hakMilikPihakBerkepentinganList}" id="line1" class="tablecloth">
                        <display:column title="Bil">
                            ${line1_rowNum}
                        </display:column>
                        <display:column property="pihak.nama" title="Nama" />
                        <display:column property="pihak.noPengenalan" title="No. Pengenalan" />
                        <display:column title="Alamat" class="alamat">
                            ${line1.pihak.alamat1}<c:if test="${line1.pihak.alamat2 ne null}">,</c:if>
                            ${line1.pihak.alamat2}<c:if test="${line1.pihak.alamat3 ne null}">,</c:if>
                            ${line1.pihak.alamat3}<c:if test="${line1.pihak.alamat4 ne null}">,</c:if>
                            ${line1.pihak.alamat4}<c:if test="${line1.pihak.poskod ne null}">,</c:if>
                            ${line1.pihak.poskod}<c:if test="${line1.pihak.negeri.kod ne null}">,</c:if>
                            ${line1.pihak.negeri.nama}
                        </display:column>
                        <display:column property="pihak.noTelefon1" title="No. Telefon" />
                        <display:column property="pihak.email" title="Alamat Email" />
                        <display:column property="jenis.nama" title="Status" class="${line1_rowNum}"/>
                    </display:table>
                    <br>

                </div>
            </c:if>
            <c:if test="${status}">
                <legend>
                    Maklumat Perintah Denda
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" cellpadding="0" cellspacing="0" id="line" >
                        <%-- <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                         <display:column title="Id Hakmilik">${line.noRujukan}</display:column>
                         <display:column title="No.Resit" property="resit.idDokumenKewangan"></display:column>--%>
                        <%--<c:if test="${actionBean.stageId eq 'maklum_byrn_denda_tmbhn' || actionBean.stageId eq 'maklum_bayaran_denda_tamb' || actionBean.stageId eq 'maklum_laporan3'}">--%>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Id Hakmilik">${line.noRujukan}</display:column>
                        <display:column title="No.Resit" property="resit.idDokumenKewangan"></display:column>
                        <display:column title="Nilai Denda Harian(RM)" property="amaun" format="{0,number, 0.00}"></display:column>
                        <display:column title="Jumlah Hari" property="tempohHari"/>
                        <display:column title="Jumlah Denda(RM)">
                            <fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlahDendaTambahan}"/>
                        </display:column>
                        <display:column title="Status Bayaran">
                            <c:if test="${line.resit.idDokumenKewangan eq null}"><font color="red">Belum Dibayar</font></c:if>
                            <c:if test="${line.resit.idDokumenKewangan ne null}">Sudah Dibayar</c:if>
                        </display:column>
                        <display:column title="Tarikh Bayar">
                            <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aaa" value="${actionBean.tarikhBayar}"/>
                        </display:column>
                        <%--</c:if>--%>
                    </display:table></div>

                <legend>
                    Maklumat Perintah Denda Tambahan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun1}" cellpadding="0" cellspacing="0" id="line" >
                        <%--<c:if test="${actionBean.stageId ne 'maklum_byrn_denda_tmbhn' && actionBean.stageId ne 'maklum_bayaran_denda_tamb' && actionBean.stageId ne 'maklum_laporan3'}">--%>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Id Hakmilik">${line.noRujukan}</display:column>
                        <display:column title="No.Resit" property="resit.idDokumenKewangan"></display:column>
                        <display:column title="Jumlah Denda(RM)" property="amaun" format="{0,number, 0.00}"></display:column>
                        <display:column title="Status Bayaran">
                            <c:if test="${line.resit.idDokumenKewangan eq null}"><font color="red">Belum Dibayar</font></c:if>
                            <c:if test="${line.resit.idDokumenKewangan ne null}">Sudah Dibayar</c:if>
                        </display:column>
                        <display:column title="Tarikh Bayar">
                            <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aaa" value="${actionBean.tarikhBayar1}"/>
                        </display:column>
                        <%--</c:if>--%>
                        <%--<display:column title="Status Bayaran">
                            <c:if test="${line.resit.idDokumenKewangan eq null}"><font color="red">Belum Dibayar</font></c:if>
                            <c:if test="${line.resit.idDokumenKewangan ne null}">Sudah Dibayar</c:if>
                        </display:column>
                        <display:column title="Tarikh Bayar">
                            <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aaa" value="${actionBean.tarikhBayar}"/>
                        </display:column>--%>

                    </display:table>
                </div>
            </c:if>
            <c:if test="${editRemedi}">
                <legend>
                    Bayaran remedi
                </legend>
                <div class="subtitle">
                    <fieldset class="aras1">
                        <c:if test="${fn:length(actionBean.hakmilikPermohonanList) ne 0}">
                            <p>
                                <label>Id Hakmilik : </label>
                                ${actionBean.hakmilik.idHakmilik}&nbsp;
                            </p>
                            <p>
                                <label>No Lot : </label>
                                ${actionBean.hakmilik.lot.nama}  ${actionBean.hakmilik.noLot}&nbsp;
                            </p>
                        </c:if>
                        <p>
                            <label><em>*</em>Tempoh Bayaran (Hari) :</label>
                            <s:text name="tempohDenda" size="15" onkeyup="validateNumber(this,this.value);" maxlength="2" id="tempohDenda"/>&nbsp;
                        </p>
                        <p>
                            <label><em>*</em>Kos Remedi (RM) :</label>
                            <s:text id="amaun" name="amaun" size="15" class="number" formatPattern="0.00" maxlength="10" onkeyup="validateNumber(this,this.value);" onblur="autoFloatRemedi(this.value);"/>&nbsp;
                        </p>
                    </fieldset>
                </div>
                <div class="content" align="center">
                    <c:if test="${fn:length(actionBean.hakmilikPermohonanList) ne 0}">
                        <display:table name="${actionBean.hakMilikPihakBerkepentinganList}" id="line1" class="tablecloth">
                            <display:column title="Bil">
                                ${line1_rowNum}
                            </display:column>
                            <display:column property="pihak.nama" title="Nama" />
                            <display:column property="pihak.noPengenalan" title="No. Pengenalan" />
                            <display:column title="Alamat" class="alamat">
                                ${line1.pihak.alamat1}<c:if test="${line1.pihak.alamat2 ne null}">,</c:if>
                                ${line1.pihak.alamat2}<c:if test="${line1.pihak.alamat3 ne null}">,</c:if>
                                ${line1.pihak.alamat3}<c:if test="${line1.pihak.alamat4 ne null}">,</c:if>
                                ${line1.pihak.alamat4}<c:if test="${line1.pihak.poskod ne null}">,</c:if>
                                ${line1.pihak.poskod}<c:if test="${line1.pihak.negeri.kod ne null}">,</c:if>
                                ${line1.pihak.negeri.nama}
                            </display:column>
                            <display:column property="pihak.noTelefon1" title="No. Telefon" />
                            <display:column property="pihak.email" title="Email" />
                            <display:column property="jenis.nama" title="Status" class="${line1_rowNum}"/>
                            <display:column title="Syer yang dimiliki">
                                ${line1.syerPembilang}/${line1.syerPenyebut}
                                <input type="hidden" name="syerPembilang${line1_rowNum-1}" value="${line1.syerPembilang}" id="syerPembilang${line1_rowNum-1}" />
                                <input type="hidden" name="syerPenyebut${line1_rowNum-1}" value="${line1.syerPenyebut}" id="syerPenyebut${line1_rowNum-1}" />
                            </display:column>
                        </display:table>
                    </c:if>

                    <c:if test="${fn:length(actionBean.senaraiTanahMilik) ne 0}">
                        <display:table  name="${actionBean.senaraiTanahMilik}" id="line" class="tablecloth">
                            <display:column title="Nombor Lot/PT" >
                                <c:if test="${line.noLot ne null}"> ${line.noLot}&nbsp; </c:if>
                                <c:if test="${line.noLot eq null}"> Tiada Data </c:if>
                            </display:column>

                            <display:column title="Kod Lot" >
                                <c:if test="${line.lot.nama ne null}"> ${line.lot.nama}&nbsp; </c:if>
                                <c:if test="${line.lot.nama eq null}"> Tiada Data </c:if>
                            </display:column>

                            <display:column title="Kategori Tanah" >
                                <c:if test="${line.kategoriTanahBaru.nama ne null}"> ${line.kategoriTanahBaru.nama}&nbsp; </c:if>
                                <c:if test="${line.kategoriTanahBaru.nama eq null}"> Tiada Data </c:if>
                            </display:column>

                            <display:column title="Catatan">
                                <c:if test="${line.catatan ne null}"> ${line.catatan}&nbsp; </c:if>
                                <c:if test="${line.catatan eq null}"> Tiada Data </c:if>
                            </display:column>
                        </display:table>
                    </c:if>

                    <br>
                    <s:button name="simpanRemedi" id="save" value="Simpan" class="btn" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                </div>
            </c:if>
            <c:if test="${viewRemedi}">
                <legend>
                    Bayaran remedi
                </legend>
                <div class="subtitle">
                    <fieldset class="aras1">
                        <c:if test="${fn:length(actionBean.hakmilikPermohonanList) ne 0}">
                            <p>
                                <label>Id Hakmilik : </label>
                                ${actionBean.hakmilik.idHakmilik}&nbsp;
                            </p>
                            <p>
                                <label>No Lot : </label>
                                ${actionBean.hakmilik.lot.nama}  ${actionBean.hakmilik.noLot}&nbsp;
                            </p>
                        </c:if>
                        <p>
                            <label>Tempoh Bayaran (Hari) :</label>
                            ${actionBean.tempohDenda}&nbsp;
                        </p>
                        <p>
                            <label>Kos Remedi (RM) :</label>
                            <fmt:formatNumber pattern="#,##0.00" value="${actionBean.amaun}"/>
                            <%--${actionBean.amaun}&nbsp;--%>
                        </p>
                    </fieldset>
                </div>
                <div class="content" align="center">
                    <c:if test="${fn:length(actionBean.hakmilikPermohonanList) ne 0}">
                        <display:table name="${actionBean.hakMilikPihakBerkepentinganList}" id="line1" class="tablecloth">
                            <display:column title="Bil">
                                ${line1_rowNum}
                            </display:column>
                            <display:column property="pihak.nama" title="Nama" />
                            <display:column property="pihak.noPengenalan" title="No. Pengenalan" />
                            <display:column title="Alamat" class="alamat">
                                ${line1.pihak.alamat1}<c:if test="${line1.pihak.alamat2 ne null}">,</c:if>
                                ${line1.pihak.alamat2}<c:if test="${line1.pihak.alamat3 ne null}">,</c:if>
                                ${line1.pihak.alamat3}<c:if test="${line1.pihak.alamat4 ne null}">,</c:if>
                                ${line1.pihak.alamat4}<c:if test="${line1.pihak.poskod ne null}">,</c:if>
                                ${line1.pihak.poskod}<c:if test="${line1.pihak.negeri.kod ne null}">,</c:if>
                                ${line1.pihak.negeri.nama}
                            </display:column>
                            <display:column property="pihak.noTelefon1" title="No. Telefon" />
                            <display:column property="pihak.email" title="Email" />
                            <display:column property="jenis.nama" title="Status" class="${line1_rowNum}"/>
                            <display:column title="Syer yang dimiliki">
                                ${line1.syerPembilang}/${line1.syerPenyebut}
                                <input type="hidden" name="syerPembilang${line1_rowNum-1}" value="${line1.syerPembilang}" id="syerPembilang${line1_rowNum-1}" />
                                <input type="hidden" name="syerPenyebut${line1_rowNum-1}" value="${line1.syerPenyebut}" id="syerPenyebut${line1_rowNum-1}" />
                            </display:column>
                        </display:table>
                    </c:if>

                    <c:if test="${fn:length(actionBean.senaraiTanahMilik) ne 0}">
                        <display:table  name="${actionBean.senaraiTanahMilik}" id="line" class="tablecloth">
                            <display:column title="Nombor Lot/PT" >
                                <c:if test="${line.noLot ne null}"> ${line.noLot}&nbsp; </c:if>
                                <c:if test="${line.noLot eq null}"> Tiada Data </c:if>
                            </display:column>

                            <display:column title="Kod Lot" >
                                <c:if test="${line.lot.nama ne null}"> ${line.lot.nama}&nbsp; </c:if>
                                <c:if test="${line.lot.nama eq null}"> Tiada Data </c:if>
                            </display:column>

                            <display:column title="Kategori Tanah" >
                                <c:if test="${line.kategoriTanahBaru.nama ne null}"> ${line.kategoriTanahBaru.nama}&nbsp; </c:if>
                                <c:if test="${line.kategoriTanahBaru.nama eq null}"> Tiada Data </c:if>
                            </display:column>

                            <display:column title="Catatan">
                                <c:if test="${line.catatan ne null}"> ${line.catatan}&nbsp; </c:if>
                                <c:if test="${line.catatan eq null}"> Tiada Data </c:if>
                            </display:column>
                        </display:table>
                    </c:if>

                    <br>
                </div>
            </c:if>

        </fieldset>
    </div>
    <br>
    <br>


</s:form>