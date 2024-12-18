<%-- 
    Document   : bidang_kuasa
    Created on : Dec 15, 2009, 5:27:12 PM
    Author     : mohd.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<script type="text/javascript">
    $(document).ready(function() {
        $('#kl1').hide();
        $('#kl2').hide();
   //   $('#lain2').hide();
   //  $('#labellain2').hide();

 });
function changeJikaLainLain(){
            if(document.getElementById('LL').checked){
                $('#lain2').show();
                $('#labellain2').show();
            }else{
                $('#lain2').val("");
            }
        }
function kl1(){if(document.form1.kuasaL1.checked == true)
{ $('#kl1').show(); }}

function kl2(){ if(document.form1.kuasaL2.checked == true)
    { $('#kl2').show(); }}
</script>

<s:form  name="form1" beanclass="etanah.view.daftar.SuratKuasaWakilActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <%--         <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SB'}">
      <fieldset class ="aras1">
          <legend>
              Jenis Surat
          </legend>
          <p>
          <label>Jenis Surat :</label>
          <s:select name="jenisSurat" id="jenisSurat" onclick="changeJikaLainLain(this.value);">
              <s:option value="0">Pilih Jenis ...</s:option>
              <s:option value="01">Am</s:option>
              <s:option value="02">Pindah Milik</s:option>
              <s:option value="03">Gadaian</s:option>
              <s:option value="04">Lepas Gadaian</s:option>
              <s:option value="05">Kaveat</s:option>
              <s:option value="06">Tarik Kaveat</s:option>
              <s:option value="07">Lepas Kaveat</s:option>
              <s:option value="08">Pajakan</s:option>
              <s:option value="09">Pajakan Kecil</s:option>
              <s:option value="10">Sewa</s:option>
              <s:option value="LL">Lain-Lain</s:option>
          </s:select>
          <br/>
      </p>
         <label id="labellain2">Sila Nyatakan (Jika Lain-lain) :</label>
          <s:text name="jenisSuratLain2" id="lain2"/>
          
          <p>
          <center><s:button name="saveJenisSurat" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'status_div');"/></center>
      </p>
      
      <p>
         <label for="status">Jenis Surat Kebenaran :</label>
         &nbsp;
            <c:if test="${actionBean.wakilKuasa.kuasaGadai eq 'Y'}">
              kebenaran gadaian
            </c:if>
            <c:if test="${actionBean.wakilKuasa.kuasaAm eq 'Y'}">
              kebenaran am
            </c:if>
            &nbsp;
      </p>
      </fieldset>
      </c:if>--%>
              <fieldset class="aras1">
            <legend>
                Jenis Surat Kebenaran
            </legend>
            <p style="color:red">
                *Sila Tandakan Jenis Surat Kebenaran Yang Terlibat.
            </p>
            
            <c:forEach var="wklKuasa"  varStatus="line" items="${actionBean.wKuasa}">
                <p>                              
                    <c:if test="${wklKuasa.jenisSuratKuasa.kod eq '1'}">
                        <c:set var="checked1" value="checked"/>                
                    </c:if> 
                    <label >&nbsp;</label>
                     <input type="checkbox" name="wakilKuasa.jenisSuratKuasa.kod" ${checked1} value="1"  />&nbsp;Kebenaran Pindahmilik
                  </p>
                <p>
                    <c:if test="${wklKuasa.jenisSuratKuasa.kod eq '2'}">
                        <c:set var="checked2" value="checked"/>                
                    </c:if> 
                    <label>&nbsp;</label>
                    <input type="checkbox" name="wakilKuasa.jenisSuratKuasa.kod" ${checked2} value="2"  />&nbsp;Kebenaran Gadaian/Cagaran
                </p>
                <p>
                   <c:if test="${wklKuasa.jenisSuratKuasa.kod eq '3'}">
                        <c:set var="checked3" value="checked"/>                
                    </c:if>  
                    <label>&nbsp;</label>
                    <input type="checkbox" name="wakilKuasa.jenisSuratKuasa.kod" ${checked3} value="3"  />&nbsp;Kebenaran Pajakan
                </p>
                <p>
                    <c:if test="${wklKuasa.jenisSuratKuasa.kod eq '4'}">
                        <c:set var="checked4" value="checked"/>                
                    </c:if> 
                    <label>&nbsp;</label>
                    <input type="checkbox" name="wakilKuasa.jenisSuratKuasa.kod" ${checked4} value="4"  />&nbsp;Kebenaran Tenansi
                </p>
                <p>
                    <c:if test="${wklKuasa.jenisSuratKuasa.kod eq '5'}">
                        <c:set var="checked5" value="checked"/>                
                    </c:if> 
                    <label>&nbsp;</label>
                    <input type="checkbox" name="wakilKuasa.jenisSuratKuasa.kod" ${checked5} value="5"  />&nbsp;Kebenaran Kaveat Lien
                </p>
                <p>
                    <c:if test="${wklKuasa.jenisSuratKuasa.kod eq '6'}">
                        <c:set var="checked6" value="checked"/>                
                    </c:if> 
                    <label>&nbsp;</label>
                    <input type="checkbox" name="wakilKuasa.jenisSuratKuasa.kod" ${checked6} value="6"  />&nbsp;Kebenaran Melepaskan Gadaian
                </p>
                <p>
                    <c:if test="${wklKuasa.jenisSuratKuasa.kod eq '7'}">
                        <c:set var="checked7" value="checked"/>                
                    </c:if> 
                    <label>&nbsp;</label>
                    <input type="checkbox" name="wakilKuasa.jenisSuratKuasa.kod" ${checked7} value="7"  />&nbsp;Kebenaran Pengkaveat
                </p>
                <p>
                    <c:if test="${wklKuasa.jenisSuratKuasa.kod eq '8'}">
                        <c:set var="checked8" value="checked"/>                
                    </c:if> 
                    <label>&nbsp;</label>
                    <input type="checkbox" name="wakilKuasa.jenisSuratKuasa.kod" ${checked8} value="8"  />&nbsp;Kebenaran Perintah Mahkamah
                </p>
                <p>
                    <c:if test="${wklKuasa.jenisSuratKuasa.kod eq '9'}">
                        <c:set var="checked9" value="checked"/>                
                    </c:if> 
                    <label>&nbsp;</label>
                    <input type="checkbox" name="wakilKuasa.jenisSuratKuasa.kod" ${checked9} value="9"  />&nbsp;Kebenaran Warganegara Asing
                </p>
                <p>
                    <c:if test="${wklKuasa.jenisSuratKuasa.kod eq '10'}">
                        <c:set var="checked10" value="checked"/>                
                    </c:if> 
                    <label>&nbsp;</label>
                    <input type="checkbox" name="wakilKuasa.jenisSuratKuasa.kod" ${checked10} value="10"  />&nbsp;Kebenaran Pindahmilik dan Gadaian
                </p>
                <p>
                    <c:if test="${wklKuasa.jenisSuratKuasa.kod eq '11'}">
                        <c:set var="checked11" value="checked"/>                
                    </c:if> 
                    <label>&nbsp;</label>
                    <input type="checkbox" name="wakilKuasa.jenisSuratKuasa.kod" ${checked11} value="11"  />&nbsp;Kebenaran Daripada Perintah Pesaka
                </p>
                <p>
                    <c:if test="${wklKuasa.kuasaLain1 != null}">
                        <c:set var="checked11" value="checked"/>                
                    </c:if>
                    <label>&nbsp;</label>
                    <input type="checkbox" name="kuasaL1" id="LL"  ${checked11} value="Y" onclick="changeJikaLainLain();" />&nbsp;Lain - Lain
                </p>

   
                    <label id="labellain2">Sila Nyatakan (Jika Lain-lain) :</label>
                    <input type="text" name="wakilKuasa.kuasaLain1" id="lain2" value="${wklKuasa.kuasaLain1}"/>
 
            </c:forEach>       
            <br/>
            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div >
                            <center> <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="saveJenisSurat" value="Simpan"/></center>
                        </div>
                    </td>
                </tr>
            </table>

        </fieldset>
    </div>
    <br>

</s:form>
