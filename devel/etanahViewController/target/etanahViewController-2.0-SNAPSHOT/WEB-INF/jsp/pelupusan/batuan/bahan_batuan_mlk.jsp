<%-- 
    Document   : bahan_batuan_mlk
    Created on : Jun 6, 2011, 10:38:12 AM
    Author     : Zaid
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"/>
<script type="text/javascript">

    $(document).ready( function(){

        var one = document.getElementById('kod1').value;
        var two = document.getElementById('kod2').value;
        var three = document.getElementById('kod3').value;
         
        if(one == 'KB'){
            //                alert(document.getElementById('mandatori2').value);
            document.getElementById('kodItem1').checked = 'true';
        }
        if(two == 'PB'){
            document.getElementById('kodItem2').checked = 'true';
        }
        if(three == 'MB'){
            document.getElementById('kodItem3').checked = 'true';
        }
            
        <c:if test="${actionBean.listHM[0].hakmilik eq null}">
            <c:if test="${actionBean.listHM[0].bandarPekanMukimBaru eq null}">
                alert("Sila masukkan Bandar/Pekan/Mukim terdahulu di bahagian tanah 1") ;
            </c:if>
        </c:if>
        <c:if test="${actionBean.listHM[0].hakmilik ne null}">
             <c:if test="${actionBean.listHM[0].hakmilik.bandarPekanMukim.nama eq null}">
                alert("Sila masukkan Bandar/Pekan/Mukim terdahulu di bahagian tanah 2") ;
            </c:if>   
        </c:if>    
            
        
    });
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode == 190)
            return false;
        return true;
    }
    function validation() {

        if($('#test').val() == ""){
            alert("Sila Masukkan Jenis Bahan Batuan") ;
            return false ;
        } 
        if($('#tempoh').val() == ""){
            alert("Sila Masukkan Jangka Masa") ;
            return false ;
        } 
        if($('#tempohUOM').val() == ""){
            alert("Sila Masukkan Unit Jangka Masa") ;
            return false ;
        } 
        if($('#isipadu').val() == ""){
            alert("Sila Masukkan Isipadu Dipohon") ;
            return false ;
        }
        //    alert(document.batuan.urusan.value) ;
        if(document.batuan.urusan.value == "PBPTG"){
            if($('#isipadu').val() <= 1001 || $('#isipadu').val() > 5000){
                alert("Sila Masukkan Isipadu Dipohon antara 1001 hingga 5000 untuk urusan ini") ;
                return false ;
            } 
        }
    <c:if test="${actionBean.kodNegeri eq '04'}">
            if(document.batuan.urusan.value == "PBPTD"){
                if($('#isipadu').val() > 1000){
                    alert("Sila Masukkan Isipadu Dipohon kurang dari 1000 untuk urusan ini") ;
                    return false ;
                } 
            }    
    </c:if>
         
    
            if($('#isipaduUOM').val() == ""){
                alert("Sila Masukkan Unit Isipadu Dipohon") ;
                return false ;
            }
    
            if($('#sbb').val() == ""){
                alert("Sila Isi Tujuan Pengeluaran") ;
                return false ;
            }
            if($('#datepicker').val() == "" ||$('#datepicker2').val() == "" ){
                alert("Sila Masukkan Maklumat Tempoh pengeluaran") ;
                return false ;
            }

            return true ;
        }

        function totDays(){
            if($('#datepicker').val() != '' && $('#datepicker2').val() != ''){
                var day1 = new Date($('#datepicker').val());
                var day2 = new Date($('#datepicker2').val());
                if(day1 <= day2){
                    var ONE_DAY = 1000 * 60 * 60 * 24;
                    var x=$('#datepicker').val().split("/");     
                    var y=$('#datepicker2').val().split("/");
                    var date1=new Date(x[2],(x[1]-1),x[0]);
                    var date2=new Date(y[2],(y[1]-1),y[0]);
                    document.getElementById('tempoh').value = Math.ceil((date2.getTime()-date1.getTime())/(ONE_DAY)+1);
                    document.getElementById('tempohUOM').value = 'HR';
                }else{
                    alert("Tarikh akhir mesti selepas tarikh mula");
                }
            }
        }

        function insertValue() {
            alert(document.getElementById('testing').checked);
    
        }

        function resetSendiri() {
        
            document.getElementById('kodItem1').checked = '';
            document.getElementById('kodItem2').checked = '';
            document.getElementById('kodItem3').checked = '';
            $('#test').val("") ;
            $('#sbb').val("") ;
            $('#ambil').val("") ;
            $('#pindah').val("") ;
            $('#sbb').val("") ;
            $('#jalan').val("") ;
            $('#lbr').val("") ;
            $('#pnjg').val("") ;
            $('#datepicker').val("") ;
            $('#datepicker2').val("") ;
            $('#tempoh').val("") ;
            $('#isipadu').val("") ;
            $('#pekerja').val("") ;
        
        
        }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.ButiranBahanBatuanActionBean" name="batuan" id="batuan">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tujuan Permohonan</legend>
            <s:hidden name="test1" id="kod1"/>
            <s:hidden name="test12" id="kod2"/>
            <s:hidden name="test13" id="kod3"/>
            <p>
                Sila isikan maklumat yang bertanda <font color="red">*</font>
            </p><br/>
            <p align="center"><font color="red">*</font>Sila pilih sekurang-kurangnya satu daripada tujuan permohonan.
            </p>
            <c:if test="${!actionBean.view}">
                <p align="center">
                    <s:checkbox name="kodItem1" id="kodItem1" />Mengeluarkan bahan batuan<br/>
                    <s:checkbox name="kodItem2" id="kodItem2"/>Memindahkan bahan batuan<br/>
                    <s:checkbox name="kodItem3" id="kodItem3" />Memproses bahan batuan<br/>     
                </p>
            </c:if>
            <c:if test="${actionBean.view}">
                <p align="center">
                    <s:checkbox name="kodItem1" id="kodItem1" disabled="true" />Mengeluarkan bahan batuan<br/>
                    <s:checkbox name="kodItem2" id="kodItem2" disabled="true"/>Memindahkan bahan batuan<br/>
                    <s:checkbox name="kodItem3" id="kodItem3" disabled="true"/>Memproses bahan batuan<br/>     
                </p>
            </c:if>
        </fieldset>
    </div><br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bahan Batuan Dipohon</legend>
            <s:hidden name="permohonan.cawangan.daerah.nama" value="${actionBean.permohonan.cawangan.daerah.nama}"/>
            <s:hidden name="permohonan.kodUrusan.kod" value="${actionBean.permohonan.kodUrusan.kod}" id="urusan"/>
            <c:if test="${actionBean.hakmilikPermohonan.hakmilik eq null}">
                <s:hidden name="hakmilikPermohonan.bandarPekanMukimBaru.nama" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}" id="bpm"/>
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.hakmilik ne null}">
                <s:hidden name="hakmilikPermohonan.bandarPekanMukimBaru.nama" value="${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}" id="bpm"/>
            </c:if>

            <%-- <p>
                 <label>Daerah :</label>
                 ${actionBean.permohonan.cawangan.daerah.nama}
             </p>
             <p>
                     <label>Mukim/Pekan/Bandar :</label>
                     ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}
              </p>--%>
            <%--<p>
                   <label><font color="red">*</font>Mukim/Pekan/Bandar :</label>
                   <s:select name="bandarPekanMukimBaru.kod" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb">
                       <s:option value="">Sila Pilih</s:option>
                       <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                   </s:select>
            </p>--%>

            <c:if test="${!actionBean.view}">
                <p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                        <label><font color="red">*</font>Jenis Batuan :</label>
                            <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                            <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="TM">Tanah Merah</s:option>                    
                            <s:option value="PS">Pasir</s:option>                    
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                        <label><font color="red">*</font>Jenis :</label>
                            <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                            <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="TM">Tanah Merah</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                        <label><font color="red">*</font>Jenis :</label>
                            <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                            <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodItemPermitBatuan}" label="nama" value="kod" />
                        </s:select>
                    </c:if>
                </p>
                <p>
                    <label><font color="red">*</font>Tujuan Pengeluaran :</label>
                        <s:textarea name="permohonan.sebab" rows="5" cols="50" class="normal_text" id="sbb"/>
                </p>
                <p>
                    <label>Kawasan pengambilan :</label>
                    <s:text name="permohonanBahanBatuan.kawasanAmbilBatuan" id="ambil" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;(No. Lot (Jika ada))
                </p>
                <p>
                    <label>Kawasan pemindahan :</label>
                    <s:text name="permohonanBahanBatuan.kawasanPindahBatuan" id="pindah" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;(No.Lot (Jika ada))
                </p>    
                <p>
                    <label>Jalan yang dilalui :</label>
                    <s:text name="permohonanBahanBatuan.jalanDilalui" id="jalan" size="50" maxlength="50"/>
                </p>
                <!--            <p>
                                <label>Bangunan Sementara :</label>
                <%--<s:select name="bangunanSementara" id="bangunanSementara" onchange="javaScript:changeBangunanSementara(this.options[selectedIndex].value)">&nbsp; Ya/Tidak
                      <s:option value="">Sila Pilih</s:option>
                       <s:option value="y">Ya</s:option>
                       <s:option value="t">Tidak</s:option>
               </s:select>--%>
           </p>-->
                <p>
                    <label>Lebar Bangunan Sementara :</label>
                    <s:text name="permohonanBahanBatuan.lebarBangunanSementara" onkeypress="return isNumberKey(event)" id="lbr"/>
                    <s:select name="lebarBangunanSementaraUom.kod" value="${actionBean.permohonanBahanBatuan.lebarBangunanSementaraUom.kod}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="JM">Meter</s:option>
                        <s:option value="JK">Kaki</s:option>
                    </s:select>
                </p>
                <p>        
                    <label>Panjang Bangunan Sementara :</label>
                    <s:text name="permohonanBahanBatuan.panjangBangunanSementara" onkeypress="return isNumberKey(event)" id="pnjg"/>
                    <s:select name="panjangBangunanSementaraUom.kod" value="${actionBean.permohonanBahanBatuan.panjangBangunanSementaraUom.kod}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="JM">Meter</s:option>
                        <s:option value="JK">Kaki</s:option>
                    </s:select>
                </p>



                <%--<p>--Melaka--
                    <label>Bina Bangunan Sementara:</label>
                    <s:select name="permohonanBahanBatuan.bangunanSementara">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="">Akan membina</s:option>
                        <s:option value="">Tidak akan menbina</s:option>
                    </s:select>&nbsp;(Jika ada sila nyatakan ukuran bangunan tersebut)
                </p>
                <p>
                    <label>Lebar:</label>
                    <s:text name="permohonanBahanBatuan.lebarBangunanSementara" id="lebar" size="20" maxlength="20" onkeyup="validateNumber(this,this.value);"/>
                    <s:select name="lebarBangunanSementara">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="KK">Kaki</s:option>
                        <s:option value="MT">Meter</s:option>
                    </s:select>
                </p>
                <p>
                    <label>Panjang:</label>
                    <s:text name="permohonanBahanBatuan.panjangBangunanSementara" id="panjang" size="20" maxlength="20" onkeyup="validateNumber(this,this.value);"/>
                    <s:select name="panjangBangunanSementara">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="KK">Kaki</s:option>
                        <s:option value="MT">Meter</s:option>
                    </s:select>
                </p>--%>

                <%--     <p>
                         <label><font color="red">*</font>Tempoh pengeluaran :</label>
                         <s:text name="permohonanBahanBatuan.tarikhMula"  id="datepicker" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"  onchange="totDays()" />
         <!--                &nbsp;hingga&nbsp;-->
                         &nbsp;<font color="#003194"><b><font color="red">*</font>hingga</b></font>&nbsp;
                         <s:text name="permohonanBahanBatuan.tarikhTamat" id= "datepicker2" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" onchange="totDays()" />
                   
                     </p>--%>
                <p>
                    <label><font color="red">*</font>Jangka Masa:</label>
                        <s:text name="permohonanBahanBatuan.tempohKeluar" size="20" id="tempoh" maxlength="3" onkeyup="validateNumber(this,this.value);" value="${actionBean.permohonanBahanBatuan.tempohKeluar}"/>
                        <s:select name="unitTempohKeluarUOM" id="tempohUOM" value="${actionBean.permohonanBahanBatuan.unitTempohKeluar.kod}">
                            <s:option value="">Sila Pilih</s:option>
                        <s:option value="HR">Hari</s:option>
                        <s:option value="B">Bulan</s:option>
                        <s:option value="T">Tahun</s:option>
                    </s:select>
                </p>
                <p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                        <label><font color="red">*</font>Jumlah isipadu :</label>
                            <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="20" formatPattern="##0.0000" onkeypress="return isNumberKey(event)"/>
                            <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="MP">Meterpadu</s:option>
                            <s:option value="KB">Ketul Batu</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                        <label><font color="red">*</font>Jumlah isipadu :</label>
                            <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="16" formatPattern="##0.0000" onkeypress="return isNumberKey(event)"/>
                            <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="MP">Meterpadu</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                        <label><font color="red">*</font>Jumlah isipadu :</label>
                            <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="16"  formatPattern="##0.0000" onkeypress="return isNumberKey(event)"/>
                            <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="MP">Meterpadu</s:option>
                        </s:select>
                    </c:if>
                </p>
                <p>
                    <label>Bilangan Pekerja :</label>
                    <s:text name="permohonanBahanBatuan.bilanganPekerja" size="20" maxlength="3" onkeypress="return isNumberKey(event)" id="pekerja"/>
                    <!--                &nbsp;orang-->
                    &nbsp;orang&nbsp;
                </p>
                <br/>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="testing33" value="Isi Semula" class="btn" onclick="resetSendiri();"/>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                </p>

            </c:if>

            <c:if test="${actionBean.view}">
                <p>
                    <label>Jenis :</label>
                    ${actionBean.permohonanBahanBatuan.jenisBahanBatu.nama}<br>
                </p>
                <p>
                    <label>Tujuan Pengeluaran :</label>
                    ${actionBean.permohonan.sebab}
                    <%--s:textarea name="permohonan.sebab" rows="5" cols="50" class="normal_text" id="sbb" readonly="true"/--%><br>
                </p>
                <p>
                    <label>Kawasan pengambilan :</label>
                    ${actionBean.permohonanBahanBatuan.kawasanAmbilBatuan}<br>
                </p>
                <p>
                    <label>Kawasan pemindahan :</label>
                    ${actionBean.permohonanBahanBatuan.kawasanPindahBatuan}<br>
                </p>    
                <p>
                    <label>Jalan yang dilalui :</label>
                    ${actionBean.permohonanBahanBatuan.jalanDilalui}<br>
                </p>
                <!--            <p>
                                <label>Bangunan Sementara :</label>
                <%--<s:select name="bangunanSementara" id="bangunanSementara" onchange="javaScript:changeBangunanSementara(this.options[selectedIndex].value)">&nbsp; Ya/Tidak
                      <s:option value="">Sila Pilih</s:option>
                       <s:option value="y">Ya</s:option>
                       <s:option value="t">Tidak</s:option>
               </s:select>--%>
           </p>-->
                <p>
                    <label>Lebar Bangunan Sementara :</label>
                    ${actionBean.permohonanBahanBatuan.lebarBangunanSementara} ${actionBean.permohonanBahanBatuan.lebarBangunanSementaraUom.nama}<br>  
                </p>
                <p>        
                    <label>Panjang Bangunan Sementara :</label>
                    ${actionBean.permohonanBahanBatuan.panjangBangunanSementara} ${actionBean.permohonanBahanBatuan.panjangBangunanSementaraUom.nama}<br>      
                </p>



                <%--<p>--Melaka--
                    <label>Bina Bangunan Sementara:</label>
                    <s:select name="permohonanBahanBatuan.bangunanSementara">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="">Akan membina</s:option>
                        <s:option value="">Tidak akan menbina</s:option>
                    </s:select>&nbsp;(Jika ada sila nyatakan ukuran bangunan tersebut)
                </p>
                <p>
                    <label>Lebar:</label>
                    <s:text name="permohonanBahanBatuan.lebarBangunanSementara" id="lebar" size="20" maxlength="20" onkeyup="validateNumber(this,this.value);"/>
                    <s:select name="lebarBangunanSementara">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="KK">Kaki</s:option>
                        <s:option value="MT">Meter</s:option>
                    </s:select>
                </p>
                <p>
                    <label>Panjang:</label>
                    <s:text name="permohonanBahanBatuan.panjangBangunanSementara" id="panjang" size="20" maxlength="20" onkeyup="validateNumber(this,this.value);"/>
                    <s:select name="panjangBangunanSementara">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="KK">Kaki</s:option>
                        <s:option value="MT">Meter</s:option>
                    </s:select>
                </p>--%>

                <%--     <p>
                         <label><font color="red">*</font>Tempoh pengeluaran :</label>
                         <s:text name="permohonanBahanBatuan.tarikhMula"  id="datepicker" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"  onchange="totDays()" />
         <!--                &nbsp;hingga&nbsp;-->
                         &nbsp;<font color="#003194"><b><font color="red">*</font>hingga</b></font>&nbsp;
                         <s:text name="permohonanBahanBatuan.tarikhTamat" id= "datepicker2" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" onchange="totDays()" />
                   
                     </p>--%>
                <p>
                    <label>Jangka Masa:</label>
                    ${actionBean.permohonanBahanBatuan.tempohKeluar} ${actionBean.permohonanBahanBatuan.unitTempohKeluar.nama}<br>    
                </p>
                <p>
                    <label>Jumlah isipadu :</label>  
                    <s:format value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohon}" formatPattern="##0.0000"/> ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.nama}<br>
                </p>
                <p>
                    <label>Bilangan Pekerja :</label>
                    ${actionBean.permohonanBahanBatuan.bilanganPekerja} orang&nbsp;<br>
                </p>
            </c:if>


        </fieldset>
    </div>
</s:form>

