<%--
    Document   : rencana_Ringkasan_LampiranA_SBMS
    Created on : Oct 10, 2012, 12:30:30 PM
    Author     : NageswaraRao
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
      function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    function searchKodSyaratNyata(index){
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSyaratNyata2&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
    }

    function searchKodSekatan(index){
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSekatan&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
    }

     
</script>


<s:form beanclass="etanah.view.stripes.pembangunan.RencanaRingkasanLampiranAActionBean">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.kertas.idKertas"/>
<div class="subtitle">
   <fieldset class="aras1">
       <legend></legend>
            <div class="content" align="center">
                    <table border="0" width="70%" align="center">
                        <tr>
                            <td align="right"><b>LAMPIRAN 'A'</b></td>
                        </tr>
                    </table>
                    
                        <table border="0" width="85%" align="center" cellspacing="10">
                            <%--<tr>
                                <td align="left"><b> ${actionBean.permohonan.kodUrusan.nama} </b></td>
                            </tr>
                            <tr>
                                <td><s:textarea rows="5" cols="150" name="tujuan" class="normal_text"/></td>
                            </tr>--%>
                            <tr>
                                <td align="left"><b> SYARAT NYATA DIHAPUSKAN DAN DIGANTIKAN DENGAN SYARAT-SYARAT NYATA SEPERTI BERIKUT :- </b></td>
                            </tr>

                        </table>
                    
                    <br/><br/>
                    <table border="0" width="85%" align="center" cellspacing="5">
                    <c:set var="i" value="0"/>
                   <c:forEach items="${actionBean.hpList}" var="line">                    
                     <tr>
                         <td width="22%"><c:if test="${i eq 0}">
                                        <b> I a) Penjenisan/Kategori</b>
                                    </c:if>
                                    <c:if test="${i eq 1}">
                                        <b> I b) Penjenisan/Kategori</b>
                                    </c:if>
                                     <c:if test="${i eq 2}">
                                         <b> I c) Penjenisan/Kategori</b>
                                    </c:if>
                                    <c:if test="${i eq 3}">
                                         <b> I d) Penjenisan/Kategori</b>
                                    </c:if>
                        <td><b>: </b> </td>
                        <td>
                        <s:select name="jenis${i}" id="kodKategoriTanah" value="${line.jenisPenggunaanTanah.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                     <s:option value="1">Pertanian</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Perusahaan</s:option>
                                    <%--<s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>--%>
                             </s:select>
                        </td>
                    </tr>
                       <tr>
                            <td> &nbsp; Jenis Hakmilik   </td>
                            <td><b>: </b> </td>
                            <td><s:select name="jenisHakmilik${i}" id="jenisHakmilik" value="${line.kodHakmilik.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodHakmilikTetapMelaka}" label="nama" value="kod"/>
                                </s:select>
                                <%--${actionBean.hp.hakmilik.kodHakmilik.nama} (${actionBean.hp.hakmilik.kodHakmilik.kod})--%> </td>
                       </tr>
                       <tr>
                            <td> &nbsp; Tempoh   </td>
                            <td><b>: </b> </td>
                            <td><s:textarea rows="3" cols="80" name="tempoh${i}" class="normal_text" value="${line.tempohPajakan}" onkeyup="validateNumber(this,this.value);"/> <%--${actionBean.hp.tempohPajakan}--%>  </td>
                       </tr>
                       <tr>
                            <td> &nbsp; Premium    </td>
                            <td><b>: </b> </td>
                            <td><s:textarea rows="3" cols="80" name="premiumDesc${i}" class="normal_text"  value="${line.keteranganKadarPremium}" />  </td>
                            <td><s:text name="premium${i}" class="normal_text" size="20" value="${line.kadarPremium}"  onkeyup="validateNumber(this,this.value);"/></td>
                       </tr>
                        <tr>
                            <td> &nbsp; Hasil    </td>
                            <td><b>:  </b></td>
                            <td><s:textarea rows="3" cols="80" name="hasilDesc${i}" class="normal_text" value="${line.keteranganCukaiBaru}"/> </td>
                            <td><s:text name="hasil${i}" class="normal_text" size="20" value="${line.cukaiBaru}" onkeyup="validateNumber(this,this.value);"/></td>
                        </tr>
                        <tr>
                            <td> &nbsp; Jenis Penggunaan Tanah    </td>
                            <td><b>:  </b></td>
                            <td><s:select name="kodKegunaanTanah${i}" id="jenisHakmilik" value="${line.kodKegunaanTanah.kod}" style="width:600px;">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKegunaanTanah}" label="nama" value="kod"/>
                                </s:select>
                                <%--${actionBean.hp.kategoriTanahBaru.nama}--%> </td>
                        </tr>
                        <tr>
                            <td> &nbsp; Syarat Nyata   </td>
                            <td><b>:  </b></td>
                            <td><s:textarea name="kodSyaratNyataBaru${i}" id="syaratNyata${i}" readonly="readonly" rows="5" cols="80" value="${line.syaratNyataBaru.kod} - ${line.syaratNyataBaru.syarat}"  />
                                <s:hidden name="syaratBaru${i}" id="kod${i}" value="${line.syaratNyataBaru.kod}" />
                                <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSyaratNyata(${i})" />
                                <%--${actionBean.hp.syaratNyataBaru.syarat}--%> </td>
                        </tr>
                        <tr>
                            <td> &nbsp; Sekatan Kepentingan  </td>
                            <td><b>:  </b></td>
                            <td><s:textarea name="kodSekatanKepentinganBaru${i}" readonly="readonly" id="sekatan${i}" rows="5" cols="80" value="${line.sekatanKepentinganBaru.kod} - ${line.sekatanKepentinganBaru.sekatan}" />
                                <s:hidden name="sekatanBaru${i}"  id="kodSktn${i}"  value="${line.sekatanKepentinganBaru.kod}" />
                                <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSekatan(${i})" />
                                <%--${actionBean.hp.sekatanKepentinganBaru.sekatan}--%> </td>
                        </tr>
                    
                    <c:set var="i" value="${i+1}" />
              </c:forEach>                   
                        <tr>
                            <td><b>II. &nbsp; Sumbangan Saliran </b>   </td>
                            <td><b>:  </b></td>
                            <td><s:textarea rows="3" cols="80" name="sumbanganSaliranDesc" class="normal_text" /><%--(keterangan_infra)--%> </td>
                            <td><s:text name="sumbanganSaliran" class="normal_text" size="20" onkeyup="validateNumber(this,this.value);"/></td>
                        </tr>
                        <tr>
                            <td><b>III. &nbsp; Bayaran Ukur  </b>  </td>
                            <td><b>:  </b></td>
                            <td><s:textarea rows="3" cols="80" name="bayaranUkur" class="normal_text" /><%--(keterangan_kadar_ukur)--%>
                               </td>
                        </tr>
                </table>
                <p align="center">
                        <s:button name="simpanSBMS" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </div>
       </fieldset>
    </div>
</s:form>