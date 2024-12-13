<%--
    Document   : kemasukan_maklumat_bangunan
    Created on : April 15, 2010, 2:22:35 PM
    Author     : Murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript">
    $(document).ready( function(){

        var as = '${actionBean.aduanStrata.skimStrata}';
        var tangga = '${actionBean.kongsiTangga}';
//        alert(as);
        if(as == "" ){
            document.getElementById("kosBiasa").checked =true;
        }
        if(tangga == "" ){
            document.getElementById("kongsiTangga").checked =true;
        }

        $('#catatan').hide();
        $('#catatan1').hide();
        //aduan_kemaduan         
        <c:forEach items="${actionBean.aduanSiasatanPK}" var="line1">             
                document.getElementById("aduan"+${line1.kodHartaBersama.kod}).checked = true;                  
            <c:if test="${line1.kodHartaBersama.kod eq '13'}">                      
                    document.getElementById("catatan").value ="${line1.catatan}";
                    $('#catatan').show();
        </c:if>
            <c:if test="${line1.kodHartaBersama.kod eq '14'}">
                    document.getElementById("catatan").value ="${line1.catatan}";
                    $('#catatan').show();
        </c:if>
    </c:forEach>         

            //Aduan_siasat_bngn
        <c:forEach items="${actionBean.siasatanPerihalBangunan}" var="line2">
            
                document.getElementById("aduan1"+'${line2.kodJenisPembangunan.kod}').checked = true;
            <c:if test="${line2.kodJenisPembangunan.kod eq 'P08'}">
                    document.getElementById("catatan1").value ="${line2.catatan}";
                    $('#catatan1').show();
        </c:if>
    </c:forEach>
            
        });

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

        function changeHide(value){
               // alert(value);
            if(value == true){
                $('#catatan').show();
            }else{
                $('#catatan').hide();
            }
        }

        function changeHide1(value){
               // alert(value);
            if(value == true){
                $('#catatan1').show();
            }else{
                $('#catatan1').hide();
            }
        }
        function test(f) {
        $(f).clearForm();
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form1" id="form1"   beanclass="etanah.view.strata.KemasukanMaklumatBangunanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>KEMASUKAN MAKLUMAT BANGUNAN</legend>
            <div class="content" align="center">
                <table border="0" width="60%">
                    <tr><td valign="top" align="right" width="40%"><b>1. Jenis Pembangunan yang dibina </b></td>
                        <td width="2%" align="center" valign="top"><b>: </b></td>
                        <td>
                            <%--<s:select name="kodP" id="kodP" style="width:280px;">
                                <s:option value="0">--Sila Pilih--</s:option>
                                  <s:options-collection collection="${listUtil.senaraiKodJenisPembangunan}" label="nama" value="kod" />
                                  <s:options-collection collection="${actionBean.senaraikodJenisPembangunan}" label="nama" value="kod" />
                            </s:select>--%>

                            <c:forEach items="${actionBean.senaraikodJenisPembangunan}" var="line3">
                                <c:if test="${line3.nama ne 'Lain-lain'}">
                                    <s:checkbox name="${line3.kod}" value="${line3.kod}" id="aduan1${line3.kod}"/> ${line3.nama}<br/>
                                </c:if>
                                <c:if test="${line3.nama eq 'Lain-lain'}">
                                    <s:checkbox name="${line3.kod}" value="${line3.kod}" id="aduan1${line3.kod}" onclick="javaScript:changeHide1(this.checked)"/> ${line3.nama}<br/>
                                    <s:textarea name="catatan1" rows="5" cols="36" id="catatan1" class="normal_text" />
                                </c:if>
                            </c:forEach>


                        </td></tr>

                    <tr><td>&nbsp;</td></tr>

                    <tr><td valign="top" width="25%" align="right"><b>2. Skim Strata </b></td>
                        <td width="2%" align="center" valign="top"><b>: </b></td>
                        <td> <s:radio name="aduanStrata.skimStrata" value="R" id="kosBiasa"/> Kos Rendah
                            <s:radio name="aduanStrata.skimStrata" value="B"/> Bukan Kos Rendah</td></tr>
                    <tr><td>&nbsp;</td><tr>                    

                    <tr><td valign="top" colspan="1" align="right"><b>3. Berkongsi Tangga 
                            </b>
                        </td>
                        <td width="2%" align="center" valign="top"><b>: </b></td>

                        <td colspan="3" valign="top"><s:radio name="kongsiTangga" value="Y" id="kongsiTangga"/> Ya
                            <s:radio name="kongsiTangga" value="T"/> Tidak </td></tr>
                    <tr><td></td></tr>

                    <tr><td>&nbsp;</td></tr>

                    <tr><td valign="top" colspan="1" align="right"><b>4. Harta Bersama </b></td>
                        <td width="2%" align="center" valign="top"><b>: </b></td>
                        <td>
                            <c:forEach items="${actionBean.senaraikodHartaBersama}" var="line">
                                <c:if test="${line.nama ne 'Lain-lain'}">
                                    <s:checkbox name="${line.nama}" value="${line.kod}" id="aduan${line.kod}"/> ${line.nama}<br/>
                                </c:if>
                                <c:if test="${line.nama eq 'Lain-lain'}">
                                    <s:checkbox name="${line.nama}" value="${line.kod}" id="aduan${line.kod}" onclick="javaScript:changeHide(this.checked)"/> ${line.nama}<br/>
                                    <%--<s:hidden name="aduanSiasatanPK[${i}].kodHartaBersama.kod" value="${line.kod}" />--%>
                                    <s:textarea name="catatan" rows="5" cols="36" id="catatan" class="normal_text" />
                                </c:if>
                            </c:forEach>
                        </td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td valign="top" colspan="1" align="right"><b>6. Bilangan Blok  </b></td>
                        <td width="2%" align="center" valign="top"><b>: </b></td>
                        <td><s:text name="bBlok"size="14" onkeyup="validateNumber(this,this.value);"/> </td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td valign="top" colspan="1" align="right"><b>7. Bilangan Unit  </b></td>
                        <td width="2%" align="center" valign="top"><b>: </b></td>
                        <td><s:text name="bUnit"size="14" onkeyup="validateNumber(this,this.value);"/> </td></tr>
                </table> <br/><br/>
                <div class="content" align="center">
                    <table border="0">
                        <tr><td><s:button name="SimpanBangunan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/>
                                <s:button  name="isiSemula" value="Isi Semula" class="btn" onclick="doSubmit(this.form,this.name,'page_div');"/></td></tr>
                    </table>
                </div>
            </div>

        </fieldset>
    </div>

</s:form>


