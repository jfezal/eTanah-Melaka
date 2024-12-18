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
        var lp = document.getElementById('lp');
        var ks = document.getElementById('ks');
    <%--  alert("ks--" + ks.checked);
      alert("lp-" + lp.checked);--%>
              if(ks.checked == true){
                  showluas(ks.value);
              }

              if(lp.checked == true){
                  showluas(lp.value);
              }

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
              var cagaran = 20/100 * jumlah;
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
              $.post('${pageContext.request.contextPath}/pelupusan/kelulusanMMKN_mlk?keputusanLulusTolak&kpsn='+i,q,
              function(data){
                  $('#page_div').html(data);
              }, 'html');
          }

          function showluas22(i,f)
          {
              var q = $(f).formSerialize();
              $.post('${pageContext.request.contextPath}/pelupusan/kelulusanMMKN_mlk?jenisLuas&luas='+i,q,
              function(data){
                  $('#page_div').html(data);
              }, 'html');
          }

          function showluas(val){
              if(val == 'P'){

                  $('#luassbhgn').hide();
                  <%--$('#luassbhgn3').hide();--%>
              }
              else if(val == 'S'){

                  $('#luassbhgn').show();
                  <%--$('#luassbhgn3').show();--%>
              }
          }

          function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

              var strNama2 = ReplaceAll(strNama," ","_");
              var strJawatan2 = ReplaceAll(strJawatan," ","_");
              var strStageID2 = "g_charting_semak";
              var objShell = new ActiveXObject("WScript.Shell");
              var sysVar = objShell.Environment("System");
              //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
              //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


              objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
          }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.KelulusanMMKNLPSPActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Keputusan</legend><br/>
            <s:hidden name="kuantitiUOM" id="kuantitiUOM"/>
            <s:hidden name="keputusan" id="keputusan"/>
            <s:hidden name="keluasan" id="keluasan"/>
            <s:hidden name="edit" id="edit"/>
            <s:hidden name="kodkuantitiUOM" id="kodkuantitiUOM"/>
            <c:if test="${actionBean.edit}">
                <div id="keputusanEdit">
                    <table>
                        <tr>
                            <td><label>Keputusan</label></td>
                            <td>:</td>
                            <td><s:radio name="keputusan" value="L" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Lulus   
                                <s:radio name="keputusan" value="T" id="keputusan" onclick="lulusTolak(this.value,this.form)"/> Tolak
                            </td>
                        </tr>
                        <tr>
                            <td><label>Bil. Mesyuarat</label></td>
                            <td>:</td>
                            <td><s:text name="mohonKertas.nomborRujukan" value="" size="20"/></td>    
                        </tr>
                        <tr>
                            <td><label>Tarikh Bersidang</label></td>
                            <td>:</td>
                            <td><s:text name="mohonKertas.tarikhSidang" id= "datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                        </tr>
                        <tr>
                            <td><label>Tarikh Sah</label></td>
                            <td>:</td>
                            <td><s:text name="mohonKertas.tarikhSahKeputusan" id= "datepicker2" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                        </tr>                    
                    </table>

                </div>
                <c:if test="${actionBean.keputusan eq 'L'}">
                    <table>
                        <tr>
                            <td><label>Luas Disyorkan</label></td>
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




                        <tr><td><label><u>Syarat-Syarat LPS</u></label></td></tr>

                        <tr>
                            <td><label>1) Bayaran</label></td>
                            <td>:</td>
                            <td>RM <s:text name="amnt" id="amnt"/>&nbsp; Setahun</td>
                        </tr>

                        <tr>
                            <td><label>2) Luas Dipohon</label></td>
                            <td>:</td>
                            <td>${actionBean.luasDipohon} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>3) Keluasan</label>
                            </td>
                            <td>:</td>
                            <td>
                                <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="P" id="lp" onclick="showluas(this.value);" />&nbsp;Keluasan Penuh
                                <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="S" id="ks" onclick="showluas(this.value);" />&nbsp;Keluasan Sebahagian
                            </td>
                        </tr>

                        <tr id="luassbhgn">
                            <td><label><em>*</em>Luas Diluluskan</label></td>
                            <td>:</td>
                            <td>
                                <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000" size="20"/>
                                <s:select name="kodLuaslulusUOM" style="width:150px" id="koduom">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodUOM}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>






                        <tr><td><label><u>Syarat-Syarat LPS Permit</u></label></td></tr>

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
                            <td>RM <s:text name="cagaran" id="cagaran" readonly="true"/> (20%) </td>
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
                            <td><label>8) Jumlah Bayaran Permit</label></td>
                            <td>:</td>
                            <td>RM <s:text name="jumlah" id="jumlah" readonly="true"/></td>
                        </tr>

                        <tr>
                            <td><label>9) Jumlah Keseluruhan Bayaran LPS dan permit</label></td>
                            <td>:</td>
                            <td>RM <s:text name="totalBayaran" id="totalBayaran" readonly="true"/></td>
                        </tr>

                        <tr>
                            <td><label>10) Syarat lain</label></td>
                            <td>:</td>
                            <td><s:textarea name="syarat" rows="6" cols="50"/></td>
                        </tr>

                    </table>
                    <p align="center">
                        <s:button name="SimpanSuratKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.keputusan eq 'T'}">
                    <p align="center">
                        <s:button name="SimpanSuratTolak" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>

            </c:if>

            <%--edit part--%>
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
                    </table>
                </div>
                <c:if test="${actionBean.keputusan eq 'L'}">
                    <table>

                        <tr><td><label><u>Syarat-Syarat LPS</u></label></td></tr>

                        <tr>
                            <td><label>1) Bayaran</label></td>
                            <td>:</td>
                            <td>RM ${actionBean.amnt}&nbsp; Setahun
                            </td>

                        </tr>

                        <tr>
                            <td><label>2) Luas Dipohon</label></td>
                            <td>:</td>
                            <td>${actionBean.luasDipohon} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>3) Keluasan</label>
                            </td>
                            <td>:</td>
                            <td>
                                <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'P'}">Keluasan Penuh</c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'S'}">Keluasan Sebahagian</c:if>
                            </td>
                            <%--<td>
                                <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="P" id="lp" onclick="showluas(this.value);" />&nbsp;Keluasan Penuh
                                <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="S" id="ks" onclick="showluas(this.value);" />&nbsp;Keluasan Sebahagian
                            </td>--%>
                        </tr>

                        <tr>
                            <td><label>Luas Diluluskan</label></td>
                            <td>:</td>
                            <td>${actionBean.hakmilikPermohonan.luasDiluluskan} ${actionBean.namaLuaslulusUOM}

                                <%-- <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000" size="20"/>
                                 <s:select name="kodLuaslulusUOM" style="width:150px" id="koduom">
                                     <s:option value="0">Sila Pilih</s:option>
                                     <s:options-collection collection="${listUtil.senaraiKodUOM}" label="nama" value="kod"/>
                                 </s:select>--%>
                            </td>
                        </tr>


                        <tr><td><label><u>Syarat-Syarat LPS Permit</u></label></td></tr>
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
                            <td>RM ${actionBean.royalti}<td>
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
                            <td>${actionBean.kuponAmaun} * ${actionBean.kuponQty} = RM ${actionBean.kupon}</td>
                        </tr>
                        <tr>
                            <td><label> Jumlah Bayaran Permit</label></td>
                            <td>:</td>
                            <td>RM <s:format value="${actionBean.jumlah}" formatPattern="###,###.00"/></td>
                        </tr>

                        <tr>
                            <td><label> Jumlah Keseluruhan Bayaran LPS dan permit</label></td>
                            <td>:</td>
                            <td>RM <s:format value="${actionBean.totalBayaran}" formatPattern="###,###.00"/></td>
                        </tr>

                        <tr>
                            <td><label> Syarat lain</label></td>
                            <td>:</td>
                            <td>${actionBean.syarat}</td>
                        </tr>
                    </table>
                </c:if>
                <c:if test="${button2 eq false}">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>Charting</legend>
                            <p>&nbsp;</p>
                            <p>
                                <s:button name="" id="btnClick" value="Charting" class="btn" onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.permohonan.idPermohonan}','${actionBean.stageId}')"/>
                            </p>
                        </fieldset>
                    </div>
                </c:if>
            </c:if>
        </fieldset>
    </div>
</s:form>