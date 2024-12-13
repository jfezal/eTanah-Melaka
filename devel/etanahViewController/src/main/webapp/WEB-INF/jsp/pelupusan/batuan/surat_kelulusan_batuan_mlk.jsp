<%-- 
Document   : surat_kelulusan_batuan_mlk
Created on : Jun 8, 2011, 11:21:27 AM
Author     : Akmal
Modified by : Shazwan
--%>


<%--<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">

    //document.all.hideshow.style.visibility = 'hidden'; 
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'})
    });
    function hideTable() { 
        if (document.getElementById) { // DOM3 = IE5, NS6 
            document.getElementById('hideshow').style.visibility = 'hidden'; 
        } 
        else { 
            if (document.layers) { // Netscape 4 
                document.hideshow.visibility = 'hidden'; 
            } 
            else { // IE 4 
                document.all.hideshow.style.visibility = 'hidden'; 
            } 
        } 
    }

    function showTable() { 
        //    $('#hideshow').show();
        if (document.getElementById) { // DOM3 = IE5, NS6 
            document.getElementById('hideshow').style.visibility = 'visible'; 
        } 
        else { 
            if (document.layers) { // Netscape 4 
                document.hideshow.visibility = 'visible'; 
            } 
            else { // IE 4 
                document.all.hideshow.style.visibility = 'visible'; 
            } 
        } 
    } 
 
    function calculateSyarat(){
        var kuantitiMax_kntt = document.getElementById('kuantitiMax_kntt').value;
       
        var kadarBayaran = document.getElementById('kadarBayaran').value;
        var cagarJalan = document.getElementById('cagarJalan').value;
        cagarJalan = cagarJalan*1;
        var jumlah = kuantitiMax_kntt * kadarBayaran;
        //alert(jumlah);
        var i = parseFloat(jumlah);
        var cagaran = i * 0.2;
        document.getElementById('royalti').value = CurrencyFormatted(jumlah);
        document.getElementById('cagaran').value = CurrencyFormatted(cagaran);
        document.getElementById('cagarJalan').value = CurrencyFormatted(cagarJalan);
        var kupon = document.getElementById('kupon').value;
        if(kupon==null)
            kupon = 0;
        document.getElementById('jumlah').value = CurrencyFormatted(jumlah+cagaran+parseFloat(kupon)+cagarJalan);
    }
    function calculateBayarKupon(){
        
        var kuponQty = document.getElementById('kuponQty').value;
       
        var kuponAmaun = document.getElementById('kuponAmaun').value;
        var jumlah = kuponAmaun * kuponQty;
        document.getElementById('kupon').value = CurrencyFormatted(jumlah);
        calculateSyarat();
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
    function lulusTolak(i,f)
    {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/surat_kelulusan_batuan?keputusanLulusTolak&kpsn='+i,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }                    
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.SuratKelulusanBatuanActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Keputusan</legend><br/>
            <s:hidden name="kuantitiUOM" id="kuantitiUOM"/>
            <s:hidden name="keputusan" id="keputusan"/>
            <s:hidden name="edit" id="edit"/>
            <s:hidden name="kodkuantitiUOM" id="kodkuantitiUOM"/>
            <s:hidden name="hakmilikPermohonan.luasDiluluskan" value="${actionBean.hakmilikPermohonan.luasTerlibat}" id="luasDiluluskan"/> 
            <!--  <s:text name="kodLuaslulusUOM" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}" id="luasLulusUOM"/>  -->

            <c:if test="${actionBean.edit}">
                <div id="keputusanEdit">
                    <table>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD' or actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            <tr>
                                <td><label>Keputusan</label></td>
                                <td>:</td>
                                <td>
                                    <c:if test="${actionBean.keputusan eq 'L'}">
                                        Lulus
                                    </c:if>
                                    <c:if test="${actionBean.keputusan eq 'T'}">
                                        Tolak
                                    </c:if>    
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBPTD' and actionBean.permohonan.kodUrusan.kod ne 'PBPTG' and actionBean.permohonan.kodUrusan.kod ne 'PBMMK' and actionBean.permohonan.kodUrusan.kod ne 'LPSP'}">
                            <tr>
                                <td><label>Keputusan</label></td>
                                <td>:</td>
                                <td><s:radio name="keputusan" value="L" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Lulus   
                                    <s:radio name="keputusan" value="T" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Tolak
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                            <tr>
                                <td><label>Keputusan</label></td>
                                <td>:</td>
                                <td><s:radio name="keputusan" value="L" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Lulus   
                                    <s:radio name="keputusan" value="T" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Tolak
                                    <s:radio name="keputusan" value="TQ" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Tangguh
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <tr>
                                <td><label>Keputusan</label></td>
                                <td>:</td>
                                <td><s:radio name="keputusan" value="L" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Lulus   
                                    <s:radio name="keputusan" value="T" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Tolak
                                    <s:radio name="keputusan" value="TG" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Tangguh
                                </td>
                            </tr>
                        </c:if>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'PBMMK' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <tr>
                                <td><label>Bil. Mesyuarat</label></td>
                                <td>:</td>
                                <td><s:text name="mohonKertas.nomborRujukan" value="" size="20"/></td>    
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'PBMMK' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <tr>
                                <td><label>Tarikh Bersidang</label></td>
                                <td>:</td>
                                <td><s:text name="mohonKertas.tarikhSidang" id= "datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'PBMMK' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <tr>
                                <td><label>Tarikh Sah</label></td>
                                <td>:</td>
                                <td><s:text name="mohonKertas.tarikhSahKeputusan" id= "datepicker2" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                            </tr>
                        </c:if>

                    </table>

                </div>
                <c:if test="${actionBean.keputusan eq 'L'}">
                    <table>
                        <tr>
                            <td><label>Luas </label></td>
                            <td>:</td>
                            <td>${actionBean.hakmilikPermohonan.luasTerlibat} 
                                <c:choose>
                                    <c:when test="${actionBean.kodUnitLuasUOM eq 'H'}">
                                        Hektar
                                    </c:when>
                                    <c:when test="${actionBean.kodUnitLuasUOM eq 'M'}">
                                        Meter Persegi
                                    </c:when>    
                                </c:choose>   

                            </td>
                        </tr>

                        <tr><td><label><u>Syarat-Syarat</u></label></td></tr>

                        <tr>
                            <td><label> Jumlah Kuantiti Disyorkan</label></td>
                            <td>:</td>
                            <td>${actionBean.kuantiti} ${actionBean.kuantitiUOM}
                            </td>
                        </tr>    
                        <tr>
                            <td><label> Jumlah Kuantiti Diluluskan</label></td>
                            <td>:</td>
                            <td><s:text name="kuantitiMax_kntt" id="kuantitiMax_kntt" onkeyup="calculateSyarat()"/> 
                                <s:select name="kuantitiMax_UOM" id="kuantitiMax_kntt" value="${actionBean.kuantitiMax_UOM}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="MP">Meterpadu</s:option>
                                    <s:option value="KB">Ketul Batu</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td><label> Tempoh Disyorkan</label></td>
                            <td>:</td>
                            <td>${actionBean.tempohDisyorkan} ${actionBean.tempohDisyorkanUOM}
                            </td>
                        </tr>
                        <tr>
                            <td><label> Tempoh yang Diluluskan</label></td>
                            <td>:</td>
                            <td><s:text name="tempoh"/> 
                                <s:select name="tempohUOM" value="${actionBean.tempohUOM}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="HR">Hari</s:option>
                                    <s:option value="B">Bulan</s:option>
                                    <s:option value="T">Tahun</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td><label>3) Kadar Royalti</label></td>
                            <td>:</td>
                            <td>RM <s:format formatPattern="#,##.00" value="${actionBean.kadar}"/> se<span style="text-transform:lowercase">${actionBean.kadarUOM}</span>  
                                <s:hidden name="kadarBayaran" id="kadarBayaran" value="${actionBean.kadar}"/>
                            </td>
                        </tr>
                        <tr>
                            <td><label>4) Jumlah Bayaran Royalti</label></td>
                            <td>:</td>
                            <td>RM <s:text name="royalti" id="royalti" readonly="true"/><td>
                        </tr>
                        <tr>
                            <td><label>5) Wang Cagaran</label></td>
                            <td>:</td>
                            <td>RM <s:text name="cagaran" id="cagaran" readonly="true"/> (*20%) </td><%--(*3)--%>
                        </tr>
                        <tr>
                            <td><label>6) Cagaran Jalan</label></td>
                            <td>:</td>
                            <td>RM <s:text name="cagarJalan" id="cagarJalan" formatPattern="###,###,##0.00" onblur="calculateBayarKupon()"/> </td>
                        </tr>
                        <tr>
                            <td><label>7) Bayaran Kupon</label></td>
                            <td>:</td>
                            <td><s:format formatPattern="#,##.00" value="${actionBean.kuponAmaun}"/>  * <s:text name="kuponQty" id="kuponQty" onkeyup="calculateBayarKupon()"/> = RM <s:text name="kupon" id="kupon" readonly="true"/></td>
                            <s:hidden name="kuponAmaun" id="kuponAmaun" value="${actionBean.kuponAmaun}"/>
                        </tr>
                        <tr>
                            <td><label>8) Jumlah Bayaran</label></td>
                            <td>:</td>
                            <td>RM <s:text name="jumlah" id="jumlah" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td><label>9) Syarat lain</label></td>
                            <td>:</td>
                            <td><s:textarea name="syarat" rows="6" cols="50" class="normal_text"/></td>
                        </tr>
                    </table>

                    <p align="center">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                            <c:if test="${actionBean.stageId eq '25TrmKptsnMMK'}">
                                <s:button name="SimpanSuratKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <c:if test="${actionBean.stageId eq '25TrmMklmnKptsn'}">
                                <s:button name="SimpanSuratKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            <c:if test="${actionBean.stageId eq '24MklmKptsn' or actionBean.stageId eq 'sedia_surat_kelulusan'}">
                                <s:button name="SimpanSuratKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                            <%--<c:if test="${actionBean.stageId eq '16TrmKptsn'}">--%>
                                <s:button name="SimpanSuratKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            <%--</c:if>--%>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMMK' and actionBean.permohonan.kodUrusan.kod ne 'LPSP' and actionBean.permohonan.kodUrusan.kod ne 'PBPTG' and actionBean.permohonan.kodUrusan.kod ne 'PBPTD'}">
                            <s:button name="SimpanSuratKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </c:if>
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                    <c:if test="${actionBean.keputusan eq 'T'}">
                        <p align="center">
                            <s:button name="SimpanSuratTolak" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.keputusan eq 'TQ'}">
                        <p align="center">
                            <s:button name="SimpanSuratTolak" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                    <c:if test="${actionBean.keputusan eq 'T'}">
                        <p align="center">
                            <c:if test="${actionBean.stageId eq '25TrmKptsnMMK'}">
                                <s:button name="SimpanSuratTolak" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </c:if>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.keputusan eq 'TQ'}">
                        <p align="center">
                            <c:if test="${actionBean.stageId eq '25TrmKptsnMMK'}">
                                <s:button name="SimpanSuratTolak" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </c:if>

                        </p>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                    <c:if test="${actionBean.keputusan eq 'T'}">
                        <p align="center">
                            <c:if test="${actionBean.stageId eq '25TrmMklmnKptsn'}">
                                <s:button name="SimpanSuratTolak" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </c:if>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.keputusan eq 'TG'}">
                        <p align="center">
                            <c:if test="${actionBean.stageId eq '25TrmMklmnKptsn'}">
                                <s:button name="SimpanSuratTolak" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </c:if>

                        </p>
                    </c:if>
                </c:if>
            </c:if>
            <c:if test="${!actionBean.edit}">
                <div id="keputusanEdit">
                    <table>
                        <tr>
                            <td><label>Keputusan</label></td>
                            <td>:</td>
                            <td>
                                <c:if test="${actionBean.permohonan.keputusan.kod eq 'L'}">Lulus</c:if>
                                <c:if test="${actionBean.permohonan.keputusan.kod eq 'T'}">Tolak</c:if>
                                <c:if test="${actionBean.permohonan.keputusan.kod eq 'TQ'}">Tangguh</c:if>
                            </td> 
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                <td>
                                    <c:if test="${actionBean.permohonan.keputusan.kod eq 'L'}">Lulus</c:if>
                                    <c:if test="${actionBean.permohonan.keputusan.kod eq 'T'}">Tolak</c:if>
                                    <c:if test="${actionBean.permohonan.keputusan.kod eq 'TG'}">Tangguh</c:if>
                                    </td> 
                            </c:if>
                        </tr>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'PBMMK' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <tr>
                                <td><label>Bil. Mesyuarat</label></td>
                                <td>:</td>
                                <td>${actionBean.mohonKertas.tajuk}</td>    
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'PBMMK' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <tr>
                                <td><label>Tarikh Bersidang</label></td>
                                <td>:</td>
                                <td><fmt:formatDate value="${actionBean.mohonKertas.tarikhSidang}" pattern="dd/MM/yyyy"/></td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <c:if test="${actionBean.keputusan eq 'L'}">
                    <table>    
                        <tr><td><label><u>Syarat-Syarat</u></label></td></tr>
                        <tr>
                            <td><label> Jumlah Kuantiti Disyorkan</label></td>
                            <td>:</td>
                            <td>${actionBean.kuantiti} ${actionBean.kuantitiUOM}
                            </td>

                        </tr>
                        <tr>
                            <td><label> Jumlah Kuantiti Diluluskan</label></td>
                            <td>:</td>
                            <td>${actionBean.kuantitiMax_kntt} ${actionBean.namaKuantitiMax_UOM}
                            </td>
                        </tr>
                        <tr>
                            <td><label> Tempoh Disyorkan</label></td>
                            <td>:</td>
                            <td>${actionBean.tempohDisyorkan} ${actionBean.tempohDisyorkanUOM}
                            </td>
                        </tr>
                        <tr>
                            <td><label> Tempoh yang Diluluskan</label></td>
                            <td>:</td>
                            <td>${actionBean.tempoh} ${actionBean.namaTempohUOM}
                            </td>
                        </tr>
                        <tr>
                            <td><label> Kadar Bayaran</label></td>
                            <td>:</td>
                            <td>RM ${actionBean.kadar} ${actionBean.kadarUOM} 
                            </td>
                        </tr>
                        <tr>
                            <td><label> Bayaran Royalti</label></td>
                            <td>:</td>
                            <td>RM <s:format formatPattern="##,####.00" value="${actionBean.royalti}"/><td>
                        </tr>
                        <tr>
                            <td><label> Wang Cagaran</label></td>
                            <td>:</td>
                            <td>RM <s:format formatPattern="###,###.00" value="${actionBean.cagaran}"/>  (20%) </td>
                        </tr>
                        <tr>
                            <td><label> Cagaran Jalan</label></td>
                            <td>:</td>
                            <td>RM <s:format formatPattern="###,###.00" value="${actionBean.cagarJalan}"/>  (20%) </td>
                        </tr>
                        <tr>
                            <td><label> Bayaran Kupon</label></td>
                            <td>:</td>
                            <td>RM <s:format formatPattern="###,###.00" value="${actionBean.kuponAmaun}"/> * ${actionBean.kuponQty} = RM <s:format formatPattern="###,###.00" value="${actionBean.kupon}"/></td>
                        </tr>
                        <tr>
                            <td><label> Jumlah Bayaran</label></td>
                            <td>:</td>
                            <td>RM <s:format value="${actionBean.jumlah}" formatPattern="###,###.00"/></td>
                        </tr>
                        <tr>
                            <td><label> Syarat lain</label></td>
                            <td>:</td>
                            <td>${actionBean.syarat}</td>
                        </tr>
                    </table>
                </c:if>
            </c:if>

            <br></br>



        </fieldset>
    </div>
</s:form>