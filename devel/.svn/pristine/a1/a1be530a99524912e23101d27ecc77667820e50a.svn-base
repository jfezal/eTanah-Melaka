<%--
    Document   : keputusanKuatkuasa
    Created on : Jul 6, 2011, 4:29:29 PM
    Author     : Shah
    Editted by Zadirul 14/07/2011
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<head>
    <script language="javascript" src="show_layer.js"></script>
    <script type="text/javascript">
        
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
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
        function validation1(){
            var ulasan = document.getElementById("ulasan").value;
            if(ulasan==""){
                alert("Sila masukkan ulasan");
                return false;
            }
            return true;
        }
        function validation2(){
            var namaPegawai = document.getElementById("namaPegawai").value;
            var noKp = document.getElementById("noKp").value;
            var jawatan = document.getElementById("jawatan").value;
            var namaJabatan = document.getElementById("namaJabatan").value;
            var namaBahagian = document.getElementById("namaBahagian").value;
            var negeri = document.getElementById("negeri").value;
            var alamat1 = document.getElementById("alamat1").value;
            if(namaPegawai==""){
                alert("Sila masukkan Nama Pegawai");
                return false;
            }
            if(noKp==""){
                alert("Sila masukkan No. Kad Pengenalan");
                return false;
            }
            if(jawatan==""){
                alert("Sila masukkan Jawatan");
                return false;
            }
            if(namaJabatan==""){
                alert("Sila masukkan Nama Jabatan");
                return false;
            }
            if(namaBahagian==""){
                alert("Sila masukkan Nama Bahagian");
                return false;
            }
            if(alamat1==""){
                alert("Sila masukkan Alamat");
                return false;
            }
            if(negeri==""){
                alert("Sila pilih Negeri");
                return false;
            }
                
            return true;
        }
        function adakes(){

            var url = '${pageContext.request.contextPath}/strata/keputusanKuatkuasa?adaKes';
            $.post(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        function tiadakes(){

            var url = '${pageContext.request.contextPath}/strata/keputusanKuatkuasa?tiadaKes';
            $.post(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        function janaReport() {
            var url = "${pageContext.request.contextPath}/strata/jana?viewReport";

            var params  = 'width='+screen.width;
            params += ', height='+screen.height;
            params += ', scrollbars=yes';
            params += ', top=0, left=0'
            params += ', fullscreen=no';

            window.open(url, "etanah", params);
            if (window.focus) {newwin.focus()}
            return false;
        }
        function test(f) {
            $(f).clearForm();
        }
    </script>
</head>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form1" id="form1" beanclass="etanah.view.strata.KeputusanKuatkuasaActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" id="tabs">
        <fieldset class="aras1">
            <c:if test="${!janasurattolak}"><legend>Semak Kertas Siasatan</legend><br><br></c:if> 
            <c:if test="${janasurattolak}"><legend>Perakuan Kertas Siasatan</legend><br><br></c:if> 
            <c:if test="${!janasurattolak}">

                <c:if test="${!(actionBean.negeri eq '04' && actionBean.kodurusan eq 'P14A')}">

                    <div align="center">
                        <table>
                            <tr>
                                <td>
                                    <!--<input type="radio" name="keputusan" value="AK" onclick="javascript:hideTable1()"/> Ada Kes-->
                                    <s:radio name="keputusan" id="keputusan1" value="AK" onclick="adakes()"/> Ada Kes
                                </td>
                                <td>&nbsp;</td>
                                <td>
                                    <!--<input type="radio" name="keputusan" value="TK" onclick="javascript:hideTable2()" /> Tiada Kes-->
                                    <s:radio name="keputusan" id="keputusan2" value="TK" onclick="tiadakes()" /> Tiada Kes
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                </c:if>
<%--
                <c:if test="${actionBean.negeri eq '04' && actionBean.kodurusan eq 'P14A'}">

                    <div align="center"  style="color: black">Wujud Kes</div>
                    <p></p><br>
                </c:if>--%>
            </c:if>
            <c:if test="${actionBean.adekes}">

                <div id="1" class="tab" align="center" display ="none">
                    <table align="center">
                        <tr>
                            <td align="right"><b>Nama Pegawai Penyiasat</b></td>
                            <td>:</td>
                            <td><s:text name="namaPegawai" id="namaPegawai" value="${actionBean.namaPegawai}"  size="40"/></td>
                        </tr>
                        <tr>
                            <td align="right"><b>No. Kad Pengenalan</b></td>
                            <td>:</td>
                            <td><s:text name="noKp" id="noKp"value="${actionBean.noKp}" onkeyup="validateNumber(this,this.value);" maxlength="12"  size="40"/> <font size="1" color="green">(Contoh:821204083344)</font>
                            </td>
                        </tr>
                        <c:if test="${actionBean.negeri eq '04'}">
                            <tr>
                                <td align="right"><b>Jawatan</b></td>
                                <td>:</td>
                                <td><s:text name="jawatan" id="jawatan" value="${actionBean.jawatan}"  size="40"/></td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.negeri eq '05'}">
                            <tr>
                                <td align="right"><b>Jawatan</b></td>
                                <td>:</td>
                                <td><s:select name="jawatan" style="width:300px" id="jawatan">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="22">Penolong Pegawai Tadbir</s:option> <%-- Kod Peranan = 22 kod_keputusan = A1--%>
                                        <s:option value="20">Penolong Pegawai Tanah</s:option>  <%-- Kod Peranan = 20 kod_keputusan = A2 --%>
                                    </s:select></td>
                            </tr>
                        </c:if>
                        <tr>
                            <td align="right"><b>Nama Jabatan</b></td>
                            <td>:</td>
                            <td><s:text name="namaJabatan" id="namaJabatan" value="${actionBean.namaJabatan}"  size="40"/></td>
                        </tr>
                        <tr>
                            <td align="right"><b>Nama Bahagian</b></td>
                            <td>:</td>
                            <td><s:text name="namaBahagian" id="namaBahagian" value="${actionBean.namaBahagian}"  size="40"/></td>
                        </tr>
                        <tr>
                            <td align="right"><b>Alamat</b></td>
                            <td>:</td>
                            <td><s:text name="alamat1" id="alamat1" value="${actionBean.alamat1}"  size="40"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td><s:text name="alamat2"  id="alamat2" value="${actionBean.alamat2}"  size="40"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td><s:text name="alamat3"  id="alamat3" value="${actionBean.alamat3}"  size="40"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td><s:text name="alamat4"   id="alamat4" value="${actionBean.alamat4}"  size="40"/></td>
                        </tr>
                        <tr>
                            <td align="right"><b>Poskod</b></td>
                            <td>:</td>
                            <td><s:text name="poskod" value="${actionBean.poskod}" onkeyup="validateNumber(this,this.value);" maxlength="5"  size="40"/></td>
                        </tr>
                        <tr>
                            <td align="right"><b>Negeri</b></td>
                            <td>:</td>
                            <td>
                                <s:select id="negeri" name="kodNegeri" style="width:150px;">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td><s:button name="simpan" class="longbtn"  value="Simpan" onclick="if(validation2()==true){doSubmit(this.form, this.name, 'page_div');}"/> &nbsp;
                                <s:button  name="isiSemula1" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/><%--<!-- <s:button name="jana" class="longbtn"  value="Jana Dokumen" onclick="janaReport()"/> &nbsp; -->--%>
                            </td>

                        </tr>
                    </table>
                </div>
            </c:if>
            <c:if test="${actionBean.tiadakes}">
                <div id="2" class="tab" align="center" >
                    <c:if test="${janasurattolak}">
                        <p>Adalah diperakukan bahawa aduan ini tiada kes seperti catatan di bawah.</p>
                        <table align="center">
                            <tr><td valign="top">
                                    <b>Sebab-sebab Penolakan:</b>
                                </td>
                                <td><s:textarea name="ulasan" readonly="true" id="ulasan" rows="5" cols="30" value="${actionBean.ulasan}" class="normal_text"/></td>
                            </tr>
                            <tr>
                                <td colspan="2">&nbsp;</td>
                            </tr>

                        </table>
                    </c:if>
                    <c:if test="${!janasurattolak}">
                        <table align="center">
                            <tr><td valign="top">
                                    <b>Sebab-sebab Penolakan:</b>
                                </td>
                                <td><s:textarea name="ulasan" id="ulasan" rows="5" cols="30" value="${actionBean.ulasan}" class="normal_text"/></td>
                            </tr>
                            <tr>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td><s:button name="simpan2" class="longbtn" value="Simpan" onclick="if(validation1()==true){doSubmit(this.form, this.name, 'page_div');}"/> &nbsp;
                                    <s:button  name="isiSemula2" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/><%--<!--<s:button name="jana" class="longbtn"  value="Jana Dokumen" onclick="janaReport()"/> &nbsp; -->--%>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>




