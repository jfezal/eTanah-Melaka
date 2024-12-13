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

 document.all.hideshow.style.visibility = 'hidden'; 
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
        var jumlah = kuantitiMax_kntt * kadarBayaran;
        //alert(jumlah);
        var cagaran = 20/100 * jumlah;
        document.getElementById('royalti').value = CurrencyFormatted(jumlah);
        document.getElementById('cagaran').value = CurrencyFormatted(cagaran);
         var kupon = document.getElementById('kupon').value;
         if(kupon==null)
                kupon = 0;
        document.getElementById('jumlah').value = CurrencyFormatted(jumlah+cagaran+parseFloat(kupon));
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
            $.post('${pageContext.request.contextPath}/pelupusan/surat_kelulusan_prmp?keputusanLulusTolak&kpsn='+i,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
    }
    function calculateBayaranPRMP(){
        
        var kuponQty = document.getElementById('kadarBayarPRMP').value;
//       alert(kuponQty);
        var kuponAmaun = document.getElementById('luasLulus').value;
//        alert(kuponAmaun);
        var koduom = document.getElementById('koduom').value;
        var jumlah = 0;
        if(koduom == '0')
            alert('Sila Nyatakan Jenis Luas Diluluskan untuk pengiraan bayaran.');
        if(koduom == 'M')
            jumlah = kuponAmaun * kuponQty;
        if(koduom == 'H'){
            kuponQty = kuponQty * 10000;
            jumlah = kuponAmaun * kuponQty;
        }
        document.getElementById('jumlahBayar').value = CurrencyFormatted(jumlah);
     }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.KeputusanPermohonanPRMPActionBean">
    <s:messages/>
    <s:errors/>
    
    <div class="subtitle">
        <fieldset class="aras1">
             <legend>Keputusan</legend><br/>
             <s:hidden name="kuantitiUOM" id="kuantitiUOM"/>
             <s:hidden name="keputusan" id="keputusan"/>
             <s:hidden name="edit" id="edit"/>
              <s:hidden name="kodkuantitiUOM" id="kodkuantitiUOM"/>
             <c:if test="${actionBean.edit}">
                 <div id="keputusanEdit">
                    <table>
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
                        <%--<c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBPTD' and actionBean.permohonan.kodUrusan.kod ne 'PBPTG'}">
                             <tr>
                               <td><label>Keputusan</label></td>
                               <td>:</td>
                               <td><s:radio name="keputusan" value="L" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Lulus   
                                   <s:radio name="keputusan" value="T" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Tolak
                               </td>
                            </tr>
                        </c:if>--%>
                   
                     <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                    <tr>
                        <td><label>Bil. Mesyuarat</label></td>
                        <td>:</td>
                        <td>${actionBean.mohonKertas.nomborRujukan}</td>    
                    </tr>
                     </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                    <tr>
                        <td><label>Tarikh Bersidang</label></td>
                        <td>:</td>
                        <td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.mohonKertas.tarikhSidang}"/></td>
                    </tr>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                    <tr>
                        <td><label>Tarikh Sah</label></td>
                        <td>:</td>
                        <td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.mohonKertas.tarikhSahKeputusan}"/> </td>
                    </tr>
                    </c:if>
                    <tr>
                        <td><label>Luas Disyorkan</label></td>
                        <td>:</td>
                        <td>${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</td>
                    </tr>
                    <tr>
                        <td><label>Luas Diluluskan</label></td>
                        <td>:</td>
                        <td>
                            <s:text name="hakmilikPermohonan.luasDiluluskan" id="luasLulus" onkeyup="calculateBayaranPRMP()"/> 
                                    <s:select name="kodLuaslulusUOM" id="koduom" value="${actionBean.kodLuaslulusUOM}" onchange="calculateBayaranPRMP()">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:option value="H">Hektar</s:option>
                                        <s:option value="M">Meter Persegi</s:option>
                            </s:select>    
                        </td>
                    </tr>
                    <tr>
                        <td><label>Bayaran</label></td>
                        <td>:</td>
                        <td>
                            <s:hidden name="bayaran" />
                           RM ${actionBean.pmi.kodItemPermit.royaltiTanahKerajaan}&nbsp;se ${actionBean.pmi.kodItemPermit.royaltiTanahKerajaanUom.nama} x Luas Diluluskan
                          <s:hidden value="${actionBean.pmi.kodItemPermit.royaltiTanahKerajaan}" name="kadarBayarPRMP" id="kadarBayarPRMP"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Jumlah Bayaran</label></td>
                        <td>:</td>
                        <td>
                            <s:text formatPattern="###,###,###.00" name="jumlahBayar" readonly="true"/>
                        </td>
                    </tr>
                    </table>
                     
                </div>
                        <c:if test="${actionBean.keputusan eq 'L'}">
                            <table>    
                                <tr><td><label><u>Syarat-Syarat</u></label></td></tr>
                            
                            <tr>
                              <td align="right" id="tdDisplay">&nbsp;</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">
                                i) Permit pertanian ini hendaklah diperbaharui pada setiap awal tahun dan akan tamat tempoh pada 31 Disember tiap-tiap tahun. 
                            </td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">ii) Permit pertanian ini tidak boleh digunakan bagi apa-apa maksud selain daripada maksud yang dinyatakan di dalamnya.</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left" colspan="2">iii) Permit pertanian ini tidak boleh dipajak, dijual atau disewakan kepada sesiapa pun.</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">iv) Permit ini akan terbatal sekiranya berlaku apa-apa pelanggaran syarat terhadap tanah ini.
                            </td>
                          </tr>
                          <tr>
                              <td align="right" colspan="4">&nbsp</td>
                          </tr>
                            </table>
                     </c:if>
                     
            <br></br>
            <p align="center">
                         <s:button name="SimpanSuratKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
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
                       </td>  
                    </tr>

                    <tr>
                        <td><label>Bil. Mesyuarat</label></td>
                        <td>:</td>
                        <td>${actionBean.mohonKertas.nomborRujukan}</td>    
                    </tr>

                    <tr>
                        <td><label>Tarikh Bersidang</label></td>
                        <td>:</td>
                        <td><fmt:formatDate value="${actionBean.mohonKertas.tarikhSidang}" pattern="dd/MM/yyyy"/></td>
                    </tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                    <tr>
                        <td><label>Tarikh Sah</label></td>
                        <td>:</td>
                        <td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.mohonKertas.tarikhSahKeputusan}"/> </td>
                    </tr>
                    </c:if>
                    <tr>
                        <td><label>Luas Disyorkan</label></td>
                        <td>:</td>
                        <td>${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</td>
                    </tr>
                    <tr>
                        <td><label>Luas Diluluskan</label></td>
                        <td>:</td>
                        <td>${actionBean.hakmilikPermohonan.luasDiluluskan} <c:if test="${actionBean.kodLuaslulusUOM eq 'H'}">Hektar</c:if><c:if test="${actionBean.kodLuaslulusUOM eq 'M'}">Meter Persegi</c:if>                             
                        </td>
                    </tr>
                    <tr>
                        <td><label>Bayaran</label></td>
                        <td>:</td>
                        <td>
                            <s:hidden name="bayaran" />
                           ${actionBean.pmi.kodItemPermit.royaltiTanahKerajaan}&nbsp;se ${actionBean.pmi.kodItemPermit.royaltiTanahKerajaanUom.nama} x Luas Diluluskan
                          <s:hidden value="${actionBean.pmi.kodItemPermit.royaltiTanahKerajaan}" name="kadarBayarPRMP" id="kadarBayarPRMP"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Jumlah Bayaran</label></td>
                        <td>:</td>
                        <td>RM <s:format formatPattern="###,###,###.00" value="${actionBean.jumlahBayar}"/>
                        </td>
                    </tr>
                    </table>
                </div>
                    <c:if test="${actionBean.keputusan eq 'L'}">
                            <table>    
                                <tr><td><label><u>Syarat-Syarat</u></label></td></tr>
                            <tr>
                                  <td align="right" id="tdDisplay">&nbsp;</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">
                                    i) Permit pertanian ini hendaklah diperbaharui pada setiap awal tahun dan akan tamat tempoh pada 31 Disember tiap-tiap tahun. 
                                </td>
                              </tr>
                              <tr>
                                  <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">ii) Permit pertanian ini tidak boleh digunakan bagi apa-apa maksud selain daripada maksud yang dinyatakan di dalamnya.</td>
                              </tr>
                              <tr>
                                  <td align="right" id="tdDisplay">&nbsp</td>
                                  <td align="center">&nbsp;</td>
                                  <td align="left" colspan="2">iii) Permit pertanian ini tidak boleh dipajak, dijual atau disewakan kepada sesiapa pun.</td>
                              </tr>
                              <tr>
                                  <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">iv) Permit ini akan terbatal sekiranya berlaku apa-apa pelanggaran syarat terhadap tanah ini.
                                </td>
                              </tr>
                              <tr>
                                  <td align="right" colspan="4">&nbsp</td>
                              </tr>
                            </table>
                     </c:if>
             </c:if>
            
            <br></br>
            
 
            
        </fieldset>
    </div>
</s:form>