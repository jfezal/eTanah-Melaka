<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
        var val = $('#nilaiTrans').val();
        if (val != '') {
            convert(val, 'nilaiTrans');
        }
        var val2 = $('#nilaiTrans2').val();
        if (val2 != '') {
            convert(val2, 'nilaiTrans2');
        }
    });

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function reloadEdit(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/maklumat_pindahmilik?reloadEdit&idHakmilik=' + val;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });

    }
    
        function doValidate() {
        
        var f = true;        
        var pegangan = $('#pegangan').val();
        var penjenisanKatg = $('#penjenisanKatg').val();
        var jenisPindahmilik = $('#jenisPindahmilik').val();
        var luasBaru =  $('#luasBaru').val();
        var kodUnitLuasBaru =  $('#kodUnitLuasBaru').val();
        var tujuan = $('#tujuan').val();
                
        if (pegangan == ''){
            alert('Sila Masukkan Jenis Sekatan.');
            return false;
        }
        
        if(penjenisanKatg == ''){
            alert('Sila Masukkan Jenis Kategori Tanah.');
            return false;
        }
        
        if (jenisPindahmilik == ''){
            alert('Sila Masukkan Jenis Pindahmilik.');
            return false;
        }
        
        if (luasBaru == ''){
            alert('Sila Masukkan Keluasan.');
            return false;
        }
        
        if (kodUnitLuasBaru == ''){
            alert('Sila Masukkan Unit Keluasan.');
            return false;
        }
        
        if (tujuan == ''){
            alert('Sila Masukkan Tujuan.');
            return false;
        }
        
        return true;
    }
    
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.MaklumatPindahMilikActionBean">
    <s:messages/>

    <fieldset class="aras1">
        <legend>Senarai Hakmilik Terlibat</legend>
        <div align="center">
            <c:if test="${fn:length(actionBean.senaraiHakmilikTerlibat) > 1}">
                <p>
                    <font size="2" color="red">
                        <b>Permohonan Melibatkan Banyak Hakmilik</b>
                    </font>
                </p>
            </c:if>
        </div>
        <div align="center">          
            <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
                Hakmilik :
            </font>
            <s:select name="idHakmilik" onchange="reloadEdit(this.value);" id="hakmilik">
                <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                    <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                        ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                    </s:option>
                </c:forEach>
            </s:select>
        </div>
        <br/>
    </fieldset>
    <br/>

    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PCMMK' and actionBean.permohonan.kodUrusan.kod ne 'PCPTD' and actionBean.permohonan.kodUrusan.kod ne 'PGDMB'}">
        <c:if test="${fn:length(actionBean.senaraiPemohon) >= 1 || fn:length(actionBean.senaraiPermohonanPihak) >= 1}">
        <fieldset class="aras1">    
            <legend>Jenis dan Kategori Penerima dan Pemohon</legend>
            <div align="center">
                    <display:table style="width:50%;" class="tablecloth" name="${actionBean.senaraiPemohon}"
                                   cellpadding="0" cellspacing="0" id="linePemohon">
                        <display:column  title="Nama Pemohon">
                           <font style="text-transform:uppercase;">${linePemohon.nama}</font>
                        </display:column>
                        <display:column  title="Kategori Pemohon">
                           <!--<font style="text-transform:uppercase;">${linePemohon.kodStatus}</font>-->
                           <%--<c:if test="${linePemohon.jenis ne 'TER'}">--%>
                               <c:if test="${linePemohon.kodStatus ne null}">
                                   <c:if test="${linePemohon.kodStatus == '1001'}" >    
                                    <font style="text-transform:uppercase;">MELAYU BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '1002'}" >    
                                    <font style="text-transform:uppercase;">BUMIPUTERA SABAH</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '1003'}" >    
                                    <font style="text-transform:uppercase;">BUMIPUTERA SARAWAK</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '1004'}" >    
                                    <font style="text-transform:uppercase;">BUKAN BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '1005'}" >    
                                    <font style="text-transform:uppercase;">WARGA ASING</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '2001'}" >    
                                    <font style="text-transform:uppercase;">SYARIKAT BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '2002'}" >    
                                    <font style="text-transform:uppercase;">SYARIKAT BUKAN BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '3001'}" >    
                                    <font style="text-transform:uppercase;">SYARIKAT ASING</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4001'}" >    
                                    <font style="text-transform:uppercase;">PERBADANAN KETUA MENTERI (CMI</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4002'}" >    
                                    <font style="text-transform:uppercase;">PERBADANAN KEMAJUAN TANAH DAN ADAT (PERTAM</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4003'}" >    
                                    <font style="text-transform:uppercase;">YAYASAN DMDI</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4004'}" >    
                                    <font style="text-transform:uppercase;">YAYASAN MELAKA</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4005'}" >    
                                    <font style="text-transform:uppercase;">MAJLIS AGAMA ISLAM MELAKA (MAIM</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '4006'}" >    
                                    <font style="text-transform:uppercase;">LAIN-LAIN</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '5001'}" >    
                                    <font style="text-transform:uppercase;">BADAN BERKANUN</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '6001'}" >    
                                    <font style="text-transform:uppercase;">NGO</font>        
                                   </c:if>
                                   <c:if test="${linePemohon.kodStatus == '7001'}" >    
                                    <font style="text-transform:uppercase;">BANK</font>        
                                   </c:if>
                               </c:if>
                            <%--</c:if>--%>
                        </display:column> 
                        <display:column  title="ID Hakmilik">
                           <font style="text-transform:uppercase;">${linePemohon.hakmilik.idHakmilik}</font>
                        </display:column>
                    </display:table>
                    <display:table style="width:50%;" class="tablecloth" name="${actionBean.senaraiPermohonanPihak}"
                                   cellpadding="0" cellspacing="0" id="linePenerima">
                        <display:column  title="Nama Penerima" class="remove">
                            <font style="text-transform:uppercase;">${linePenerima.nama}</font></a>
                        </display:column>
                        <display:column  title="Jenis Penerima" class="remove">
                            <font style="text-transform:uppercase;">${linePenerima.jenis.nama}</font></a>
                        </display:column>
                        <display:column  title="Kategori Penerima">
                            <!--linePenerima<font style="text-transform:uppercase;">${linePenerima.kodStatus}</font>-->
                            <c:if test="${linePenerima.jenis ne 'TER'}">
                               <c:if test="${linePenerima.kodStatus ne null}">
                                   <c:if test="${linePenerima.kodStatus == '1001'}" >    
                                    <font style="text-transform:uppercase;">MELAYU BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '1002'}" >    
                                    <font style="text-transform:uppercase;">BUMIPUTERA SABAH</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '1003'}" >    
                                    <font style="text-transform:uppercase;">BUMIPUTERA SARAWAK</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '1004'}" >    
                                    <font style="text-transform:uppercase;">BUKAN BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '1005'}" >    
                                    <font style="text-transform:uppercase;">WARGA ASING</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '2001'}" >    
                                    <font style="text-transform:uppercase;">SYARIKAT BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '2002'}" >    
                                    <font style="text-transform:uppercase;">SYARIKAT BUKAN BUMIPUTERA</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '3001'}" >    
                                    <font style="text-transform:uppercase;">SYARIKAT ASING</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '4001'}" >    
                                    <font style="text-transform:uppercase;">PERBADANAN KETUA MENTERI (CMI</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '4002'}" >    
                                    <font style="text-transform:uppercase;">PERBADANAN KEMAJUAN TANAH DAN ADAT (PERTAM</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '4003'}" >    
                                    <font style="text-transform:uppercase;">YAYASAN DMDI</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '4004'}" >    
                                    <font style="text-transform:uppercase;">YAYASAN MELAKA</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '4005'}" >    
                                    <font style="text-transform:uppercase;">MAJLIS AGAMA ISLAM MELAKA (MAIM</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '4006'}" >    
                                    <font style="text-transform:uppercase;">LAIN-LAIN</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '5001'}" >    
                                    <font style="text-transform:uppercase;">BADAN BERKANUN</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '6001'}" >    
                                    <font style="text-transform:uppercase;">NGO</font>        
                                   </c:if>
                                   <c:if test="${linePenerima.kodStatus == '7001'}" >    
                                    <font style="text-transform:uppercase;">BANK</font>        
                                   </c:if>
                               </c:if>
                            </c:if>
                        </display:column>
                    </display:table>
            </div>
          
        </fieldset>
        </c:if>
        
        
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Tambahan</legend>
                <font size="2" color="red">
                    <b>Semua yang bertanda * adalah wajib dimasukkan.</b>
                </font>
                <p>
                    <label>No Kebenaran FIC (jika ada) :</label> 
                    <s:text name="permohonan.penyerahNoRujukan" id="penyerahNoRujukan" size="20" />    
                </p>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MCKMM' || actionBean.permohonan.kodUrusan.kod eq 'MCPTG' 
                              || actionBean.permohonan.kodUrusan.kod eq 'PJKMM' || actionBean.permohonan.kodUrusan.kod eq 'SWKMM' 
                              || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' || actionBean.permohonan.kodUrusan.kod eq 'PMKMM' 
                              || actionBean.permohonan.kodUrusan.kod eq 'PJPTD' || actionBean.permohonan.kodUrusan.kod eq 'PMPTD' 
                              || actionBean.permohonan.kodUrusan.kod eq 'MCPTD' || actionBean.permohonan.kodUrusan.kod eq 'SWPTD'}">
                    <p>
                        <label><font color="red">*</font>Jenis Sekatan :</label> 
                                <s:select name="hakmilikPermohonan.pegangan" id="pegangan" onchange="javaScript:changePegangan(this.options[selectedIndex].value);">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="AM">Kod A-Melayu</s:option>
                                    <s:option value="AB">Kod A-Bumiputera</s:option>
                                    <s:option value="B">Kod B</s:option>
                                    <s:option value="LL">Lain-lain</s:option>
                                </s:select>
                    </p>
                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MCKMM' || actionBean.permohonan.kodUrusan.kod eq 'MCPTG' 
                              || actionBean.permohonan.kodUrusan.kod eq 'PJKMM' || actionBean.permohonan.kodUrusan.kod eq 'SWKMM' 
                              || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' || actionBean.permohonan.kodUrusan.kod eq 'PMKMM' 
                              || actionBean.permohonan.kodUrusan.kod eq 'PJPTD' || actionBean.permohonan.kodUrusan.kod eq 'PMPTD' 
                              || actionBean.permohonan.kodUrusan.kod eq 'MCPTD' || actionBean.permohonan.kodUrusan.kod eq 'SWPTD'}">
                    <p>
                        <label><font color="red">*</font>Jenis Kategori Tanah :</label> 
                                <s:select name="hakmilikPermohonan.penjenisan" id="penjenisanKatg" onchange="javaScript:changePenjenisanKatg(this.options[selectedIndex].value);">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="1">Pertanian</s:option>
                                    <s:option value="2">Kediaman</s:option>
                                    <s:option value="3">Perusahaan</s:option>
                                    <s:option value="4">Perniagaan</s:option>
                                </s:select>
                    </p>
                </c:if>

                <p>
                    <label>Nilai Transaksi (RM) :</label>
                    <s:text name="permohonanUrusan.perjanjianAmaun" id="nilaiTrans"
                            onchange="convert(this.value, this.id);" formatPattern="#,###.00" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>Tempoh Bayaran :</label>

                    <s:text name="tempohTahun" size="5" maxlength="3"  onkeyup="validateNumber(this,this.value);"/> Tahun
                    <s:text name="tempohBulan" size="5" maxlength="2"  onkeyup="validateNumber(this,this.value);"/> Bulan

                    <%--<s:text name="permohonanUrusan.perjanjianTempohTahun" size="20" maxlength="3"  onkeyup="validateNumber(this,this.value);"/>--%>
                </p>
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMKMM' || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' 
                               || actionBean.permohonan.kodUrusan.kod eq 'PMPTD'}">
                <p> 
                    <label><font color="red">*</font>Jenis Pindahmilik :</label>
                        <s:select name="pindahMilik" id="jenisPindahmilik" onchange="javaScript:changeJenisPindahmilik(this.options[selectedIndex].value);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiJenisPindahmilik}" label="nama" value="kod"/>
                        </s:select> 
                </p>            
                </c:if>
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJKMM' || actionBean.permohonan.kodUrusan.kod eq 'PJPTD'}">
                    <p> 
                        <label><font color="red">*</font>Keluasan :</label>
                        <s:text name="hakmilikPermohonan.luasBaru" id="luasBaru" formatPattern="###0.0000"/>
                    </p>
					
                    <p>
                       <label><font color="red">*</font>Unit Keluasan :</label> 
                       <s:select style="text-transform:uppercase"  name="kodUnitLuasBaru" id="kodUnitLuasBaru" onchange="javaScript:changeKodUnitLuasBaru(this.options[selectedIndex].value);"> 
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodUOMByJarak3}" label="nama" value="kod" />
                        </s:select>
                    </p>
                </c:if>
                
                <p>
                    <label><font color="red">*</font>Tujuan :</label>
                    <s:textarea name="permohonanUrusan.sebab" id="tujuan" rows="4" cols="70" class="normal_text"/>
                </p>
                <p>
                    <label>Keterangan :</label>
                    <s:textarea name="permohonanUrusan.catatan" rows="4" cols="70" class="normal_text"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanPindahMilik" id="save" value="Simpan" class="btn" onclick="if(doValidate())doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCMMK' or actionBean.permohonan.kodUrusan.kod eq 'PCPTD' or actionBean.permohonan.kodUrusan.kod eq 'PGDMB'}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Pindah Milik</legend>
                <p>
                    <label>Nilai Transaksi (RM) :</label>
                    <s:text name="permohonanUrusan.perjanjianAmaun" id="nilaiTrans"
                            onchange="convert(this.value, this.id);" formatPattern="#,###.00" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>Tempoh Bayaran (Tahun) :</label>
                    <s:text name="tempohTahun" size="20" maxlength="3"  onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>Tujuan :</label>
                    <s:textarea name="permohonanUrusan.sebab" rows="4" cols="70" class="normal_text"/>
                </p>
                <p>
                    <label>Keterangan :</label>
                    <s:textarea name="permohonanUrusan.catatan" rows="4" cols="70" class="normal_text"/>
                </p>
            </fieldset>
        </div>
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Gadaian</legend>
                <p>
                    <label>Nilai Transaksi (RM) :</label>
                    <s:text name="permohonanUrusan2.perjanjianAmaun" id="nilaiTrans2"
                            onchange="convert(this.value, this.id);" formatPattern="#,###.00" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>Tempoh Bayaran (Tahun) :</label>
                    <s:text name="tempohTahunSerentak" size="20" maxlength="3"  onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>Tujuan :</label>
                    <s:textarea name="permohonanUrusan2.sebab" rows="4" cols="70" class="normal_text"/>
                </p>
                <p>
                    <label>Keterangan :</label>
                    <s:textarea name="permohonanUrusan2.catatan" rows="4" cols="70" class="normal_text"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanPindahMilik2" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>
</s:form>

