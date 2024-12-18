<%--
    Document   : hakmilik_baru_strata
    Created on : 27 Aug 2012, 3:41:04 PM
    Author     : ida    
--%>
<div id="maklumatHakmilik">
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
    <%@ page contentType="text/html;charset=windows-1252"%>
    <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
    <%-- <script type="text/javascript"
             src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>--%>

    <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery.bestupper.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
    <script type="text/javascript">
        
        $(document).ready( function(){
            //filterKodSeksyen();
            //filterKodGunaTanah();
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}); 
        });

        /*function p(v){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });

            $.get("${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?idHakmilik="+v,
            function(data){
                //alert(data);
                $("#perincianHakmilik").show();
                $("#perincianHakmilik").html(data);
                $.unblockUI();
            });
        }*/

        function kiraCukaiKelompok(id,i){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            //var kodTanah = $("#kodTanah").val();
            //var kodBpm = $("#kodBpm").val();
            var kodUOM = $("#kodUOM"+i).val();
            var luas = $("#luas"+i).val();
            //alert(id);
            //alert(kodUOM);
            //alert(luas);
            //var kodRizab = $("#kodRizab").val();
            //alert(kodRizab);
            //var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonan?kiraCukaiKelompok&idHakmilik='+id+'&kodUOM='+kodUOM+'&luas='+luas,
            function(data){
                if(data != ''){

                    $('#cukai'+i).val(convert(data,'cukai'+i));
                    $.unblockUI();
                }
            }, 'html');
        }
        
        function validate() {
            var sValue = document.getElementById("noPelan1").value;
            var pattern2 = /^\d{5}([\-]\d{4})?$/;
            var pattern = /^[0-9@!#\$\^%&*()+=\-\[\]\\\';,\.\/\{\}\|\":<>\? ]+$/;
            if (pattern.test(sValue)) {
                alert("Your Input is valid : "+sValue);
                return true;
            }
            alert("Input except Number & Special Characters only !");
            return false;

        }
    
        function validation2(){
            var noPelan = document.getElementById("noPelan1").value;
            var noPu = document.getElementById("noPu").value;
           
            if(noPelan==""){
                alert("Sila masukkan No PA(B)");
                return false;
            }
            if(noPu==""){
                alert("Sila masukkan No. Helaian");
                return false;
            }
                     
            return true;
        }
    
        function removeNonNumeric( strString )
        {
            var strValidCharacters = "1234567890-";
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

        function toggleSelectDocument(selectInput, i){
            if(selectInput.checked){
                if ($('#bilSalinan' + i).val() == ''){
                    $('#bilSalinan' + i).val(1);
                }
            } else{
                var c = document.getElementById("semua");
                if (c.checked) c.checked = false;

                if ($('#bilSalinan' + i).val() != null){
                    $('#bilSalinan' + i).val('');
                }
            }
        }

        function clearForm(){
            var url = "${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonan?showFormHm";
            $.post(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        
        
        function resetPAB()
        {
            
            var r=confirm("Semua rekod PA(B) akan dihapuskan. Anda pasti?");
            if (r==true)
            {
                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?showFormHm'+param;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
                
            } else {
                return;
            }           
        }
            
        function removeMultipleMohonHakmilik(){
            if (confirm('Adakah anda pasti untuk hapus hakmilik ini')){

                var param = '';
                $('.remove2').each(function(index){
                    var a = $('#checkbox'+index).is(":checked");
                    if(a) {
                        param = param + '&idMohonHakmilik=' + $('#checkbox'+index).val();
                    }
                });

                if(param == ''){
                    alert('Sila Pilih Hakmilik terlebih dahulu.');
                    return;
                }

                var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteMultipleMohonHakmilik'+param;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
            }
        }

        function selectAll(a){
            
            var size = '${fn:length(actionBean.hakmilikPermohonanList)}';
            //alert("Size "+size);  
            for (i = 0; i < size; i++){
                var c = document.getElementById("checkbox" + i); 
                if (c == null) break;
                c.checked = a.checked;
                //alert("Values "+c.value);
                // if ($("#semua").is(':checked')) {
                //     c.setAttribute("checked", "checked");
                //   } else {
                //       c.removeAttribute("checked");
                //   }
            }
        }
        function perincianTanah(v,w){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            $.get("${pageContext.request.contextPath}/strata/permohonanStrata?showForm&idHakmilikPihakBerkepentingan="+v+"&idHakmilik="+w,
            function(data){
                $("#perincianTanah").show();
                $("#perincianTanah").html(data);
                $.unblockUI();
            });
        }
        function perincianHakmilik(v){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });                    

            $.get("${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonan?showHakmilikStrataReg2&idHakmilik="+v,
            function(data){                
                $("#perincianHakmilik").show();
                $("#perincianHakmilik").html(data);
                $.unblockUI();
            });

        }
        
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
            
    </script>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <s:form beanclass="etanah.view.strata.StrataMaklumatHakmilikPermohonanActionBean">
        <div class="subtitle displaytag">            
            <s:messages/>

            <br /><br />
            <center><p><font color="blue"><b>Jumlah Unit Syer: <%--<s:text name="hakmilik.jumlahUnitSyer" readonly="true" size="8"/>--%><s:text name="jumlahUnitSyor" readonly="true" size="8"/>
                        </b></font></p></center>
            <br /> 
            <fieldset class="aras1">  
                <c:if test="${actionBean.stageId eq 'kemasukan' || actionBean.stageId eq 'g_keputusan2'}">
                    <lagend>Maklumat No PA(B) dan No Helaian</lagend> <br/>
                    <!--<font size="2" color="red">*Sila masukkan nombor PA(B) dan 'Pilih' petak yang terlibat, seterusnya klik butang 'Simpan'</font>-->
                    <div class="instr-fieldset">
                        <font color="green">Arahan: Sila masukkan No PA(B) dan No Helaian seterusnya 'Pilih' petak yang terlibat, seterusnya klik butang 'Simpan' </font>
                    </div>
                    <p align="left">
                        <br/><b>No PA(B) &nbsp;&nbsp; :</b>
                        <%-- <s:text name="noPelan1" id="noPelan1" onkeyup="validateNumber2(this,this.value);"/>--%>
                        <s:text name="noPelan1" id="noPelan1" onkeyup="validateNumber(this,this.value);"/>


                        <br/><b>No Helaian :</b>
                        <%-- <s:text name="noPelan1" id="noPelan1" onkeyup="validateNumber2(this,this.value);"/>--%>
                        <s:text name="noPu" id="noPu"/>
                    </p>  
                    <br/>
                </c:if>
                <c:if test="${actionBean.stageId eq 'kemasukan'}">
                    <p><s:button name="simpanNoPAB" id="simpan"  value="Simpan" class="btn" onclick="if(validation2()==true){doSubmit(this.form, this.name, 'page_div')}"/>&nbsp;<s:button name="reset" id="reset" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/></p>
                    <p></p>
                </c:if>

                <c:if test="${actionBean.stageId eq 'g_keputusan2'}">
                    <p><label>&nbsp;</label><s:button name="simpanNoPAB" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;<s:button class="btn" value="Isi Semula" name="resetForm" onclick="clearForm()"/></p>
                </c:if>

                <c:if test="${actionBean.stageId eq 'kemasukanBckup'}">
                    <br/><br/> <lagend>Maklumat No Helaian</lagend> <br/>
                    <!--<font size="2" color="red">*Sila masukkan nombor Helaian dan 'Pilih' petak yang terlibat, seterusnya klik butang 'Simpan'</font>-->
                    <div class="instr-fieldset">
                        <font color="green">Arahan: Sila masukkan nombor helaian dan 'Pilih' petak yang terlibat, seterusnya klik butang 'Simpan' </font>
                    </div>
                    <p align="left">
                        <br/><b>No Helaian :</b>
                        <%-- <s:text name="noPelan1" id="noPelan1" onkeyup="validateNumber2(this,this.value);"/>--%>
                        <s:text name="noPu" id="noPu"/>
                    </p>
                    <p><s:button name="simpanNoPu" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;<s:button name="reset2" id="reset2" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></p>
                </c:if>
                <br/><br/>

                Maklumat Hakmilik Baru <br/><br/><br/>
                <c:if test="${actionBean.stageId ne 'g_keputusan2'}" >
                    <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">

                        <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                        <display:table class="tablecloth" style="width:100%;" requestURI="${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" pagesize="150" id="line">
                            <c:if test="${actionBean.stageId eq 'kemasukan'}">
                                <display:column title="Pilih <br/><input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' class='readonly'/>">
                                    <center><s:checkbox name="checkbox${line_rowNum-1}" value="${line.hakmilik.idHakmilik}" id="checkbox${line_rowNum-1}"  class="readonly" /></center>
                                </display:column>
                            </c:if>
                            <display:column title="No"><center>${line_rowNum}</center></display:column>
                            <display:column title="ID Hakmilik"><center><a href="#" onclick="perincianHakmilik('${line.hakmilik.idHakmilik}');return false;"><c:if test="${line.hakmilik.noPelan eq null}"><font color="red">${line.hakmilik.idHakmilik}</font></c:if><c:if test="${line.hakmilik.noPelan ne null}">${line.hakmilik.idHakmilik}</c:if></a></center>
                                <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                            </display:column>
                            <display:column title="No Bangunan">
                                <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                                    <center>${line.hakmilik.noBangunan}</center>
                                </c:if>
                            </display:column>
                            <display:column title="No Tingkat">
                                <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                                    <center>${line.hakmilik.noTingkat}</center>
                                </c:if>
                            </display:column>
                            <display:column title="No Petak">
                                <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                                    <center>${line.hakmilik.noPetak}</center>
                                </c:if>
                                <c:if test="${line.hakmilik.kodKategoriBangunan.kod eq 'L'}">
                                    <center>${line.hakmilik.noPetak}</center>
                                </c:if>
                            </display:column>

                            <display:column title="Unit Syer">
                                <center>${line.hakmilik.unitSyer}</center>
                            </display:column>
                            <display:column title="No PA(B)">
                                <center>${line.hakmilik.noPelan}</center>
                            </display:column>
                            <display:column title="No Helaian">
                                <center>${line.hakmilik.noPu}</center>
                            </display:column>


                        </display:table>
                        &nbsp;
                    </c:if>
                </c:if>

                <c:if test="${actionBean.stageId eq 'g_keputusan2'}">
                    <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">

                        <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                        <display:table class="tablecloth" style="width:100%;" requestURI="${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanBaruList}" cellpadding="0" cellspacing="0" pagesize="150" id="line">

                            <display:column title="Pilih <br/><input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' class='readonly'/>">
                                <center><s:checkbox name="checkbox${line_rowNum-1}" value="${line.hakmilik.idHakmilik}" id="checkbox${line_rowNum-1}"  class="readonly" /></center>
                            </display:column>

                            <display:column title="No"><center>${line_rowNum}</center></display:column>
                            <display:column title="ID Hakmilik"><center><a href="#" onclick="perincianHakmilik('${line.hakmilik.idHakmilik}');return false;"><c:if test="${line.hakmilik.noPelan eq null}"><font color="red">${line.hakmilik.idHakmilik}</font></c:if><c:if test="${line.hakmilik.noPelan ne null}">${line.hakmilik.idHakmilik}</c:if></a></center>
                                <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                            </display:column>
                            <%-- <c:if test="${actionBean.stageId eq 'kemasukan'}">--%>

                            <%--</c:if>--%>
                            <%-- <c:if test="${actionBean.stageId ne 'kemasukan'}">
                            <display:column title="ID Hakmilik"><center>${line.hakmilik.idHakmilik}</center>
                                <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                            </display:column>
                            </c:if>--%>

                            <display:column title="No Bangunan">
                                <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                                    <center>${line.hakmilik.noBangunan}</center>
                                </c:if>
                            </display:column>
                            <display:column title="No Tingkat">
                                <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                                    <center>${line.hakmilik.noTingkat}</center>
                                </c:if>
                            </display:column>
                            <display:column title="No Petak">
                                <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                                    <center>${line.hakmilik.noPetak}</center>
                                </c:if>
                                <c:if test="${line.hakmilik.kodKategoriBangunan.kod eq 'L'}">
                                    <center>${line.hakmilik.noPetak}</center>
                                </c:if>
                            </display:column>

                            <display:column title="Unit Syer bagi Petak">
                                <center>${line.hakmilik.unitSyer}</center>
                            </display:column>
                            <display:column title="No PA(B)">
                                <center>${line.hakmilik.noPelan}</center>
                            </display:column>
                            <display:column title="No Helaian">
                                <center>${line.hakmilik.noPu}</center>
                            </display:column>


                        </display:table>
                    </c:if>
                    &nbsp;
                </c:if>
                <br>
            </fieldset>
        </div>
    </s:form>
    <div id="perincianHakmilik">
    </div>
</div>



