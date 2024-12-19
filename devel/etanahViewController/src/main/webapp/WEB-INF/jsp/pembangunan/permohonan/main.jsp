<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--<html>--%>
    <%--<head>--%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <title>Kaunter: Utama</title>


    <%--</head>--%>
    <%--<body>--%>
         <style type="text/css">
            .origWidth {
                width:500px;
            }
        </style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
        <script language="javascript" >
            $(document).ready(function() {
                $('#next').attr("disabled", true);
                var kodNegeri = document.getElementById("kn");
                
                $(".wideselect")
                .focus(function(){
                    $(this).data("origWidth", $(this).css("width")).css("width", "auto");
                })
                .blur(function(){
                    //$(this).css("width", $(this).data("origWidth"));
                    $(this).data("origWidth", $(this).css("width")).css("width", "450px");
                    //$(".wideselect").css("width"),"150";
                });
                $('input:text').each(function(){
                    $(this).focus(function() { $(this).addClass('focus')});
                    $(this).blur(function() { $(this).removeClass('focus')});
                });
                $('select').each(function(){
                    $(this).focus(function() { $(this).addClass('focus')});
                    $(this).blur(function() { $(this).removeClass('focus')});
                });
                dialogInit('Carian Hakmilik');
            });
        </script>
        <script language="javascript">
            function entsub() {
                document.getElementById("next").click();
                return true;
            }

            function updateSelect(idx){
                var textKodUrusanKod = document.getElementById('kodUrusanKod' + idx);
                if (textKodUrusanKod.value.length > 0){
                    var selectKodUrusan = document.getElementById('kodUrusan' + idx);
                    selectKodUrusan.selectedIndex = 0;
                    var kod = textKodUrusanKod.value.toUpperCase();
                    for (var i = 0; i < selectKodUrusan.options.length; ++i){
                        if (selectKodUrusan.options[i].value == kod){
                            selectKodUrusan.selectedIndex = i;
                            updateJabatan(idx, selectKodUrusan.options[i].parentNode.label);
                            break;
                        }
                    }
                    if (selectKodUrusan.selectedIndex == 0){
                        alert('Kod Urusan ' + kod + ' tidak dijumpai.');
                    }
                }

            }

            function updateKod(i){
                var selectKodUrusan = document.getElementById('kodUrusan' + i);
                if (selectKodUrusan.selectedIndex > 0){
                    var textKodUrusanKod = document.getElementById('kodUrusanKod' + i);
                    textKodUrusanKod.value = selectKodUrusan.options[selectKodUrusan.selectedIndex].value;
                    updateJabatan(i, selectKodUrusan.options[selectKodUrusan.selectedIndex].parentNode.label);
                }
            }

            function checkingPermohonan(id){
                $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckIDPermohonan&idPermohonan=" + id,
                    function(data){
                        if(data =='1'){
//                            alert('Rayuan menggunakan ID permohonan '+id+' telah dibuat');
                            alert('ID permohonan '+id+' telah digunakan');
                            $("#idPermohonan").val("");
                            $('#nxt').attr("disabled", "true");
                        }else{$('#nxt').removeAttr("disabled");}
                    });
            }

            function updateJabatan(whichItem, namaJabatan){
                var selectJabatan = document.getElementById('kodJabatan' + whichItem);
                for (i = 0; i < selectJabatan.length; i++){
                    if (selectJabatan.options[i].text == namaJabatan){
                        selectJabatan.selectedIndex = i;
                        break;
                    }
                }
            }

            function selectUrusanForJabatan(whichItem){
                var kodJabatan = $("#kodJabatan" + whichItem + " option:selected").text();

                var found = false;
                var selectUrusan = document.getElementById("kodUrusan" + whichItem);
                for (i = 0; i < selectUrusan.length; i++){
                    if (selectUrusan.options[i].parentNode.label == kodJabatan){
                        selectUrusan.selectedIndex = i;
                        found = true;
                        // update kod urusan
                        var selectKodUrusan = document.getElementById('kodUrusan' + whichItem);
                        var textKodUrusanKod = document.getElementById('kodUrusanKod' + whichItem);
                        textKodUrusanKod.value = ''; // nullify first
                        if (selectKodUrusan.selectedIndex > 0){
                            textKodUrusanKod.value = selectKodUrusan.options[selectKodUrusan.selectedIndex].value;
                        }

                        break;
                    }
                }

                if (!found) selectUrusan.selectedIndex = 0;
            }

            function checking(inputTxt, type){
                if(type == "hakmilik"){
                    var a = $("#hakmilik").val();
                }if(type == "akaun"){
                    var a = $("#noAkaun").val();
                }
                inputTxt = inputTxt.toUpperCase();
                if(inputTxt != ""){
                    $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilik&idHakmilik=" + inputTxt+"&type="+type,
                    function(data){
                        var dataLength= data.length;
                        if((data == '1')||(data == '7')||(dataLength>2)){
                            $("#msg" + inputTxt).html("OK");
                            $('#next').removeAttr("disabled");
                            <%--$('#next').removeAttr("disabled", true);--%>
                            if(type == 'hakmilik')
                                $("#hakmilik").val(inputTxt.toUpperCase());
                        }
                        else if(data =='0'){
                            $("#hakmilik").val("");
                            $("#noAkaun").val("");
                            if(type == "hakmilik"){
                                alert("ID Hakmilik " + inputTxt + " tidak wujud!");
                            }if(type == "akaun"){
                                alert("Nombor Akaun " + inputTxt + " tidak wujud!");;
                            }
                            }else if(data =='2'){
                                $("#hakmilik").val("");
                                $("#noAkaun").val("");
                                if(type == "hakmilik"){
                                    alert("Notis 8A telah dikenakan ke atas ID Hakmilik " + a + ".");
                                }if(type == "akaun"){
                                    alert("Notis 8A telah dikenakan ke atas Nombor Akaun " + a + ".");
                                }
                            } else if(data =='3'){
                                $("#hakmilik").val("");
                                $("#noAkaun").val("");
                                alert("ID Hakmilik " + a + " masih belum didaftarkan.");
                            }else if(data =='4'){
                                $("#hakmilik").val("");
                                $("#noAkaun").val("");
                                if(type == "hakmilik"){
                                    alert("Terdapat Permohonan Bayaran Ansuran bagi ID Hakmilik " + a + ".");
                                }if(type == "akaun"){
                                    alert("Terdapat Permohonan Bayaran Ansuran bagi Nombor Akaun " + a + ".");
                                }
                            }else if(data =='5'){
                                $("#hakmilik").val("");
                                $("#noAkaun").val("");
                                alert("ID Hakmilik " + a + " telah rosak.");
                            }else if(data =='6'){
                                $("#hakmilik").val("");
                                $("#noAkaun").val("");
                                if(type == "hakmilik"){
                                    alert("ID Hakmilik " + a + " telah dibatalkan.");
                                }if(type == "akaun"){
                                    alert("Nombor Akaun " + a + " telah dibatalkan.");
                                }
                            }else if(data =='9'){
                                $("#hakmilik").val("");
                                $("#noAkaun").val("");
                                if(type == "hakmilik"){
                                    alert("Terdapat Permohonan Bayaran Ansuran bagi ID Hakmilik " + a + ".");
                                }
                            }
                            else if(data =='8'){
                                <%--$("#hakmilik").val("");--%>
                                <%--$("#noAkaun").val("");--%>
                                if(type == "hakmilik"){
                                    alert("Notis 6A telah dikenakan ke atas ID Hakmilik " + a + ".");
                                }
                                $('#next').removeAttr("disabled");
                            }
                            else{
                                $("#hakmilik").val("");
                                $("#noAkaun").val("");
                                alert("ID Hakmilik " + inputTxt + " tidak wujud!");
            <%--entsub();--%>
                            }
                        });
                    }
                }

                function RunProgram(strUserID, strPsswd, strNoResit){
                    document.getElementById("tutup").click();
                    var objShell = new ActiveXObject("WScript.Shell");
                    var sysVar = objShell.Environment("System");
                    //alert(sysVar("eTanahGIS"));
                    objShell.Run(sysVar("eTanahGIS") + " " + strUserID + " " + strPsswd + " " + strNoResit);
                    self.close();
                }
        </script>

        <span class=title>Kaunter Utama</span><br/>

        <span class=instr>Medan bertanda <em>*</em> adalah mandatori.</span>
        <stripes:messages />
        <stripes:errors />

        <!--  KUTIPAN CUKAI -->
<%--
        <stripes:form beanclass="etanah.view.stripes.hasil.KutipanHasilActionBean" id="kutipan_hasil">
            <div class="subtitle">
                
                <fieldset class="aras1">

                    <legend>Kutipan Cukai Tanah</legend>
                    <div class="instr-fieldset">
                        <font color="red">Perhatian :</font><br>
                        <c:if test="${actionBean.kodNegeri ne 'melaka'}"><em>*</em>Masukkan ID Hakmilik untuk bayaran tunggal.<br></c:if>
                        <em>*</em>Sila tekan butang Bayaran Pukal untuk membuat bayaran secara pukal.<br>
                    </div>
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <p><label for="noAkaun">Nombor Akaun</label>
                            <stripes:text name="akaun.noAkaun" id="noAkaun" size="30" onblur="javascript:checking(this.value, 'akaun');" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                    </c:if>

                    <p>
                        <label for="idHakmilik"><c:if test="${actionBean.kodNegeri ne 'melaka'}"><em>*</em>
                                <stripes:text name="" value="n9" id="kn" style="display:none"/></c:if>ID Hakmilik : </label>
                        <stripes:text name="hakmilik.idHakmilik" id="hakmilik" onblur="javascript:checking(this.value, 'hakmilik');" size="30" onkeyup="this.value=this.value.toUpperCase();"
                                      onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>

                    <p>
                        <label>&nbsp;</label>
                        <stripes:submit name="search" value="Seterusnya" class="btn" id="next"/>&nbsp;
                        <stripes:submit name="bayranPukal" value="Bayaran Pukal" class="btn"/>
                       
                    </p>

                </fieldset>

                <stripes:submit name="updateUrusanJabatan" style="display:none;" />

            </stripes:form>
--%>
            <p></p>

            <!--  PERMOHOHONAN/PERSERAHAN-->

            <stripes:form action="/pembangunan/permohonan" id="main_kaunter">

                <fieldset class="aras1">

                    <legend>Pembangunan Urusan</legend>

                    <c:set scope="request" var="pilihanKodUrusan" value="${actionBean.pilihanKodUrusan}" />
                    <c:set scope="request" var="senaraiJabatan" value="${listUtil.senaraiKodJabatan}" />
                    <c:set scope="request" var="senaraiUrusanPendaftaran" value="${listUtil.senaraiUrusanPendaftaran}" />

                    <p><label for="kodUrusankod" class="labelspoc"><em>*</em>Urusan</label><nobr>

                        <stripes:select name="urusan.kodJabatan" id="kodJabatan0" onchange="selectUrusanForJabatan(0)" >
                            <option value="0">Pilih Unit...</option>
                            <c:forEach items="${senaraiJabatan}" var="j" >
                                <option value="${j.kod}" >${j.nama}</option>
                            </c:forEach>
                        </stripes:select>

                        <stripes:text name="urusan.kodUrusan" id="kodUrusanKod0" onblur="javascript:updateSelect(0);" onkeyup="this.value=this.value.toUpperCase();" size="6" />

                        <c:set scope="request" var="jabatanNama" value="${pilihanKodUrusan[0].jabatanNama}" />

                        <stripes:select name="urusan.kodUrusanPilih" id="kodUrusan0" onchange="javascript:updateKod(0);">
                            <stripes:option label="Pilih Urusan..."  value="0" />
                            <optgroup label="${jabatanNama}" />
                            <c:forEach items="${pilihanKodUrusan}" var="i" >
                                <c:if test="${jabatanNama != i.jabatanNama}" >
                                    <c:set var="jabatanNama" value="${i.jabatanNama}" />
                                    <optgroup label="${jabatanNama}" />
                                </c:if>
                                <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                            </c:forEach>
                        </stripes:select>
                        
                    </nobr>
                    </p>

                    <p><label for="berkaitanSebelum" class="labelspoc">&nbsp;</label><nobr>
                    
                        <c:set scope="request" var="kodUrusanSebelum" value="${actionBean.kodUrusanSebelum}" />
                        
                        <c:if test="${!empty kodUrusanSebelum}">
	                        <stripes:checkbox name="urusan.berkaitanSebelum" /> 
	                            Urusan ini berangkai dengan Urusan sebelum (${kodUrusanSebelum}).
	                        </nobr>
                        </c:if>
                    </p>
                    
                    <p>
                        <label>&nbsp;</label>
                        <stripes:submit name="Step2" value="Seterusnya" class="btn" />
                    </p>

                </fieldset>
                <%--<stripes:text name="mod" id="mod3" style="display:none;"/>--%>
                <stripes:submit name="updateUrusanJabatan" style="display:none;" />

            </stripes:form>
            <p></p>
            <!-- KESINAMBUNGAN URUSAN -->
<%--
            <stripes:form action="/kaunter/kesinambungan" id="main_kesinambungan">

                <fieldset class="aras1">

                    <legend>Kesinambungan Urusan</legend>

                    <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                        <input type="text" name="permohonan.idPermohonan" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();" onblur="checkingPermohonan(this.value)"/>
                    </p>

                    <p>
                        <label>&nbsp;</label>
                        <stripes:submit name="checkPermohonan" value="Seterusnya" class="btn" id="nxt" disabled="true"/>
                    </p>

                </fieldset>

                <stripes:submit name="updateUrusanJabatan" style="display:none;" />

            </stripes:form>
            <p></p>
            <c:if test="${actionBean.kodNegeri eq 'melaka' and actionBean.ptg ne false}">
                <stripes:form action="/hasil/jualan_pelan" id="beli_pelan">
                    <fieldset class="aras1">
                        <legend>Pembelian Pelan</legend>
                        <p></p>
                        <p>
                            <label>&nbsp;</label>
                            
                            <stripes:button name="" onclick="RunProgram ('${actionBean.pguna.idPengguna}','${actionBean.pguna.password}','0')" class="btn" value="Beli Pelan"/>
                            <a href="<%= request.getContextPath()%>/logout" class="tooltips" Title="Log Keluar" onclick="return logout()" id="tutup"></a>
                        </p>
                        <p></p>
                    </fieldset>

                    <stripes:submit name="updateUrusanJabatan" style="display:none;" />
                </stripes:form>
            </c:if>
            <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                <stripes:form action="/hasil/beli_pelan" id="beli_pelan">
                    <fieldset class="aras1">
                        <legend>Pembelian Pelan</legend>
                        <p></p>
                        <p>
                            <label>&nbsp;</label>
                            <stripes:submit name="" value="Beli Pelan" class="btn" />
                        </p>
                        <p></p>
                    </fieldset>
                    <stripes:submit name="updateUrusanJabatan" style="display:none;" />
                </stripes:form>
            </c:if>
--%>
            <br/>

           

        </div>
    