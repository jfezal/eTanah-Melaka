<%--
    Document   : penyerahan_hakmilik
    Created on : Dec 3, 2009, 10:02:55 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="etanah.model.Pengguna"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Penyerahan Hakmilik</title>

        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
        <c:set value="${actionBean.urusan}" var="kodUrusan"/>
        <%
            Pengguna p = (Pengguna) request.getSession().getAttribute("_KEY_USER");
            //String kodDaerah = p.getKodCawangan().getDaerah().getKod();
        %>
        <script type="text/javascript">
            $(document).ready(function()
            {
                //-------------------------------------------------------
                /*shows the loading div every time we have an Ajax call*/
                $("#loadingbar").bind("ajaxSend", function(){
                    $(this).show();
                    $("#partialKodBPM").hide();
                }).bind("ajaxComplete", function(){
                    $(this).hide();
                    $("#partialKodBPM").show();
                });
                //-------------------------------------------------------
            }) 

            function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;

                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                },'html');
            }
            function filterKodBPM(f){
                var kodDaerah = f
                //alert(kodDaerah);
                //var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?senaraiKodBPMByDaerah&kodDaerah='+kodDaerah+'&popup=true',
                function(data){
                    if(data != ''){
                        $('#partialKodBPM').html(data);
                    }
                }, 'html');
            }
            function changevalue(value){    
                    
                if(value == "Y")
                {
                $('#labelJenisDaerah').show();
                $('#namaJenisDaerah').show();                
                $('#kodJenisHakmilik').show();
                $('#kodJenisDaerah').show();
                $('#namaJenisHakmilikPTD').show();
                $('#namaJenisHakmilik').hide();
                $('#namaJenisHakmilik').val("");
                $('#kodJenisHakmilik').val("");
                }
                if(value == "N")
                {
                $('#namaJenisHakmilik').show();
                $('#namaJenisHakmilikPTD').hide();
                $('#namaJenisHakmilikPTD').val("");
                $('#kodJenisHakmilik').show();
                $('#labelJenisDaerah').hide();
                $('#kodJenisDaerah').hide();
                $('#namaJenisDaerah').hide();
                $('#kodJenisDaerah').val("");
                $('#namaJenisDaerah').val("");
                $('#kodJenisHakmilik').val("");
                }
            }
            function checkPelan(f){
                //alert(f);
                var noLot = f
                var kodDaerah = $('#kodDaerah').val();
                var kodBPM = $('#kodBPM').val();
                var kodNegeri = $('#kodNegeri').val();
                //alert(kodDaerah);
                //var q = $(e).formSerialize();
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkPelan&noLot='+noLot+'&kodDaerah='+kodDaerah
+'&kodNegeri='+kodNegeri+'&kodBPM='+kodBPM,
                function(data){
                    if(data != '1'){
                        //alert(data);
                        alert('Pelan untuk no lot '+ noLot +' tiada');
                        $("#noLot").val("");
                        $("#noLot").focus();
                    }
                }, 'html');
            }
            function checkHakmilik(f){
                var idHakmilikAsal = f;
                if(f !== ''){

                    //alert("kodDaerah"+kodDaerah);
                    //alert("kodBPM"+kodBPM);
                    //alert("kodNegeri"+kodNegeri);
                    $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkHakmilik&idHakmilikAsal='+idHakmilikAsal,
                    function(data){
                        if(data != '1'){
                            //alert('Hakmilik Asal '+ idHakmilikAsal +' tiada');
                            $("#errors").html('<b>Hakmilik Asal '+ idHakmilikAsal +' tiada</b>');
                            //$("#errors").append();
                            $("#errors").show();
                            //$("#noLot").val("");
                            //$("#noLot").focus();
                            $("#janaBtn").attr("disabled", "true");
                        }else{
                            $("#errors").hide();
                            $("#messages").html('<b>Hakmilik Asal '+ idHakmilikAsal +' wujud.Sila teruskan kemasukan.</b>');
                            $("#messages").show();
                            //alert($('#idHakmilikAsal').val().substr(4,2));
                            $("#kodBPM").val($('#idHakmilikAsal').val().substr(4,2));
                            $("#namaBPM").val($('#idHakmilikAsal').val().substr(4,2));
                            $("#janaBtn").attr("disabled", "");

                        }
                    }, 'html');

                }else{
                    //$("#errors").hide();
                    $("#errors").html('<b>Sila masukkan hakmilik asal</b>');
                    $("#errors").show();
                    $("#janaBtn").attr("disabled", "true");
                }
            }

            function checkTotalPihak(f){
                var idHakmilikAsal = f;
                //alert("kodDaerah"+kodDaerah);
                //alert("kodBPM"+kodBPM);
                //alert("kodNegeri"+kodNegeri);
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkTotalHakmilik&idHakmilikAsal='+idHakmilikAsal,
                function(data){
                    //alert(data);
                    if(data != '0'){

                        //alert('Hakmilik Asal '+ idHakmilikAsal +' tiada');
                        //$("#noLot").val("");
                        //$("#noLot").focus();

                        $("#totalHakmilik").val(data);
                    }else{
                        //alert($('#idHakmilikAsal').val().substr(4,2));
                        //$("#kodBPM").val($('#idHakmilikAsal').val().substr(4,2));
                        //$("#namaBPM").val($('#idHakmilikAsal').val().substr(4,2));
                        $("#totalHakmilik").val("1");
                    }
                }, 'html');
            }



            $(document).ready( function(){
                <c:if test="${actionBean.urusan eq 'HTIR'}">
                    $('#kodJenisHakmilik').hide();
                    $('#namaJenisHakmilik').hide();
                </c:if>        
               
                $('#namaJenisHakmilikPTD').hide();
                $('#kodJenisDaerah').hide();
                $('#namaJenisDaerah').hide();    
                
                //$("#messages").html('<b>Sila masukkan maklumat berkaitan untuk menjana hakmilik baru</b>');
                //$("#messages").show();
            <%-- if (urusan.equals("HSPB") || urusan.equals("HKPB")
            || urusan.equals("HSPS") || urusan.equals("HKPS")
            || urusan.equals("HKPTK") || urusan.equals("HSBTK")
            || urusan.equals("HKBTK") || urusan.equals("HKPS")
            || urusan.equals("HSPTK")) {--%>
            <c:if test="${actionBean.urusan eq 'HSPB' || actionBean.urusan eq 'HKPB' || actionBean.urusan eq 'HSPS' ||
                          actionBean.urusan eq 'HKPS' || actionBean.urusan eq 'HTSPS'
                          || actionBean.urusan eq 'HKPTK' || actionBean.urusan eq 'HSBTK' ||
                          actionBean.urusan eq 'HKBTK' || actionBean.urusan eq 'HKPS'}">
                                  $("#totalHakmilik").blur(function(){
                                      if($("#totalHakmilik").val() < 2){
                                          alert("Bilangan Hakmilik Tidak Boleh Kurang Dari 2");
                                          $("#totalHakmilik").val(2);
                                      }
                                      if(isNaN($("#totalHakmilik").val())){
                                          alert("Bilangan Hakmilik Tidak Sah!");
                                          $("#totalHakmilik").val(1);
                                      }
                                  });
            </c:if>
                    $("#totalHakmilik").blur(function(){
                        if($("#totalHakmilik").val() < 1){
                            alert("Bilangan Hakmilik Tidak Boleh Kurang Dari 1");
                            $("#totalHakmilik").val(1);
                        }
                        if(isNaN($("#totalHakmilik").val())){
                            alert("Bilangan Hakmilik Tidak Sah!");
                            $("#totalHakmilik").val(1);
                        }
                    });
                    maximizeWindow();
                    $("#lot").val('1');
                    //$("#namaNegeri").val().toUpperCase();
                    filterKodBPM($('#kodDaerah').val());

                    $("#namaDaerah").val($("#kodDaerah").val());

                    $("#Step4").click(function(){
                            <c:if test="${actionBean.urusan ne 'HTIR'}">
                        if($("#namaJenisHakmilik").val() == ""){
                            alert("Sila pilih jenis hakmilik yang akan dikeluarkan");
                            return false;
                        }
                    
                        //else if($("#namaBPM").val() == ""){
                        //    alert("Sila pilih bandar pekan mukim");
                        //    return false;
                        //}
                        else{
                            return true;
                        }
                        </c:if>
                            <c:if test="${actionBean.urusan eq 'HTIR'}">
                                if ($('input[name=serah]:checked').length <= 0) {
                                alert("Sila pastikan kesemua maklumat dimasukkan");
                                return false;
                                }
                                
                                else if(document.getElementById('PTG').checked && $("#namaJenisHakmilik").val() == ""){
                                alert("Sila pilih jenis hakmilik yang akan dikeluarkan");
                                return false;
                                }
                                
                                else if(document.getElementById('PTD').checked){
                                    if($("#namaJenisHakmilikPTD").val() == ""){
                                    alert("Sila pilih jenis hakmilik yang akan dikeluarkan");
                                    return false;
                                    }
                                    
                                    else if($("#namaJenisDaerah").val() == ""){
                                    alert("Sila pilih daerah");
                                    return false;
                                    }                                    
                                
                                }
 
                                else {
                                    return true;
                                }
                            </c:if>
                            
                    });

                    $("#namaDaerah").change( function() {
                        var valueDaerah = $("#namaDaerah").val();
                        $("#kodDaerah").val(valueDaerah);
                    });
                    $("#kodDaerah").blur( function() {
                        var valueDaerah = $("#kodDaerah").val();
                        $("#namaDaerah").val(valueDaerah);
                    });
                    $("#kodBPM").blur( function() {
                        var valueBPM = $("#kodBPM").val();
                        $("#namaBPM").val(valueBPM);
                    });
                    $("#namaBPM").change( function() {
                        var valueBPM = $("#namaBPM").val();
                        $("#kodBPM").val(valueBPM);
                    });
                    $("#namaJenisHakmilik").change( function() {
                        var valueJenisHakmilik = $("#namaJenisHakmilik").val();
                        $("#kodJenisHakmilik").val(valueJenisHakmilik);
                    });
                    $("#namaJenisHakmilikPTD").change( function() {
                        var valueJenisHakmilik = $("#namaJenisHakmilikPTD").val();
                        $("#kodJenisHakmilik").val(valueJenisHakmilik);
                    });
                    $("#namaJenisDaerah").change( function() {
                        var valueJenisDaerah = $("#namaJenisDaerah").val();
                        $("#kodJenisDaerah").val(valueJenisDaerah);
                    });
                    $("#kodJenisHakmilik").blur( function() {
                        var valueJenisHakmilik = $("#kodJenisHakmilik").val();
                        $("#namaJenisHakmilik").val(valueJenisHakmilik);
                        $("#namaJenisHakmilikPTD").val(valueJenisHakmilik);
                    });
                    $("#kodJenisDaerah").blur( function() {
                        var valueJenisDaerah = $("#kodJenisDaerah").val();
                        $("#namaJenisDaerah").val(valueJenisDaerah);
                    });

                    $("#idHakmilikAsal").blur(function(){
                        var kodUrusan = '${kodUrusan}';
                        if(kodUrusan == 'HKPS' || kodUrusan == 'HTSPS' || kodUrusan == 'HSPS' || kodUrusan == 'HKPB' || kodUrusan == 'HSPB'
                            || kodUrusan  == 'HKBTK' || kodUrusan  =='HKPTK' || kodUrusan == 'HSBTK' || kodUrusan =='HSPTK'){
                            //alert($(this).val());
                            checkHakmilik($(this).val());
                            checkTotalPihak($(this).val());
                        }else{
                            checkHakmilik($(this).val());
                        }
                    });


                });
        </script>
    </head>

    <body>
        <%-- <s:errors/>
         <s:messages/>--%>
        <div class="messages" id="messages" style="display:none;"></div>
        <div class="errors" id="errors" style="display:none;"></div>
        <div class="subtitle">

            <s:useActionBean beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean" var="list"/>
            <s:form action="/daftar/kaunter" id="kemasukanPerincianHakmilik">
                <c:if test="${actionBean.urusan ne 'HSTK' && actionBean.urusan ne 'HKTK' && actionBean.urusan ne 'HKTKP' &&
                              actionBean.urusan ne 'HSCTK' && actionBean.urusan ne 'HKCTK' && actionBean.urusan ne 'HSBTK' &&
                              actionBean.urusan ne 'HKABT' && actionBean.urusan ne 'HKBTK' && actionBean.urusan ne 'HKSTK' &&
                              actionBean.urusan ne 'HSPTK' && actionBean.urusan ne 'HKPTK' && actionBean.urusan ne 'HSBM' &&
                              actionBean.urusan ne 'HKBM' && actionBean.urusan ne 'HSTKP' && actionBean.urusan ne 'HKTHK' &&
                              actionBean.urusan ne 'HSTHK' && actionBean.urusan ne 'BMSTM'}">
                    <c:set var="disabled" value="disabled"/>
                </c:if>
                <s:hidden name="idHakmilik" />
                <p class="title">Urusan : ${actionBean.namaUrusan}</p>
                <fieldset class="aras1">

                    <legend>
                        Hakmilik Baru <c:if test="${actionBean.urusan ne 'BMSTM'}">/  Hakmilik Sambungan</c:if>
                    </legend>
                    <%-- <p>
                         <label>No Perserahan :</label>
                         ${actionBean.p.idPermohonan}
                         &nbsp
                     </p>--%>
                    <%-- <p>
                         <label>Urusan :</label>
                         ${actionBean.urusan} - ${actionBean.p.kodUrusan.nama}
                         &nbsp
                     </p>--%>
                <c:if test="${actionBean.urusan eq 'HTIR'}">
                    <p><label>Jenis perserahan :</label>
                    <s:radio name="serah" value="Y" id="PTD" onclick="javaScript:changevalue(this.value)" onchange="clearText('');"></s:radio> PTD
                    <s:radio name="serah" value="N" id="PTG" onclick="javaScript:changevalue(this.value)" onchange="clearText('');"></s:radio> PTG
                </p>
                </c:if>
                    <c:if test="${actionBean.urusan eq 'HKTHK' || actionBean.urusan eq 'HSTHK'}">
                        <p>
                            <label>ID Hakmilik Untuk Diconvert :</label>
                            <s:text name="hakmilik.idHakmilik" id="idHakmilik" onblur="this.value=this.value.toUpperCase();"/>
                        </p>
                    </c:if>

                    <p>
                        <label>Jumlah Hakmilik :</label>
                        <s:text name="totalHakmilik" id="totalHakmilik" size="4"/>
                    </p>
                    <%--TODO: GET NAMA & KOD NEGERI FROM SESSION--%>
                    <c:if test="${disabled ne 'disabled'}">
                        <p>
                            <label>Negeri :</label>
                            <s:text name="kodNegeri" id="kodNegeri" readonly="true" size="4"/> - <s:text name="namaNegeri" id="namaNegeri" readonly="true" 
style="text-transform: uppercase;"/>
                        </p>
                        <p>
                            <label>Daerah :</label>
                            <s:text name="kodDaerah" size="4" id="kodDaerah" readonly="true" />
                            -
                            <s:select name="hakmilik.daerah.kod" id="namaDaerah" onchange="filterKodBPM(this.value);">
                                <%-- <s:option value="">-- Sila Pilih --</s:option>--%>
                                <s:options-collection collection="${actionBean.senaraiKodDaerah}" label="nama" value="kod"/>
                            </s:select>


                        </p>
                        <center><div id="loadingbar"><img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img"/></div></center>
                        <div id="partialKodBPM">

                        </div>
                    </c:if>
                    <p>
                        <c:if test="${actionBean.urusan eq 'BMSTM'}">
                            <label id="labelJenisHakmilik">Jenis Hakmilik :</label>
                            <s:text name="kodJenisHakmilik" size="4" id="kodJenisHakmilik" onblur="this.value=this.value.toUpperCase();"/> -
                            <%--<s:text name="kodJenisHakmilik" size="4" id="kodJenisHakmilik" style="text-transform:uppercase;"/> ---%>
                            <s:select name="hakmilik.kodHakmilik.kod" id="namaJenisHakmilik">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodHakmilik}" label="nama" value="kod" />
                            </s:select>
                        </c:if>                        
                        
                        <c:if test="${actionBean.urusan ne 'HTIR' && actionBean.urusan ne 'BMSTM'}">
                            <label id="labelJenisHakmilik">Jenis Hakmilik :</label>
                            <s:text name="kodJenisHakmilik" size="4" id="kodJenisHakmilik" onblur="this.value=this.value.toUpperCase();"/> -
                            <%--<s:text name="kodJenisHakmilik" size="4" id="kodJenisHakmilik" style="text-transform:uppercase;"/> ---%>
                            <s:select name="hakmilik.kodHakmilik.kod" id="namaJenisHakmilik">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodHakmilik}" label="nama" value="kod" />
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.urusan eq 'HTIR'}">
                            <label id="labelJenisHakmilik">Jenis Hakmilik :</label>
                            <s:text name="kodJenisHakmilik" size="4" id="kodJenisHakmilik" onblur="this.value=this.value.toUpperCase();"/> -
                            <%--<s:text name="kodJenisHakmilik" size="4" id="kodJenisHakmilik" style="text-transform:uppercase;"/> ---%>
                            <s:select name="hakmilik.kodHakmilik.kod" id="namaJenisHakmilik">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodHakmilik}" label="nama" value="kod" />
                            </s:select>
                            <s:select name="hakmilik.kodHakmilik.kod" id="namaJenisHakmilikPTD">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodHakmilik}" label="nama" value="kod" />
                            </s:select>
                        </c:if>
                    </p>
                    <p>                         
                        <c:if test="${actionBean.urusan eq 'HTIR'}">
                            <label id="labelJenisDaerah">Daerah :</label>
                            <s:text name="kodJenisDaerah" size="4" id="kodJenisDaerah" onblur="this.value=this.value.toUpperCase();"/> - 
                            <s:select name="hakmilik.daerah.kod" id="namaJenisDaerah">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodDaerah}" label="nama" value="kod" />
                            </s:select>
                        </c:if>                               
                    </p>
                    <c:if test="${actionBean.urusan eq 'HT' || actionBean.urusan eq 'HTSC' || actionBean.urusan eq 'HTSPB'
                                  || actionBean.urusan eq 'HTSPS' || actionBean.urusan eq 'HTSPV'}">
                          <p><label>No Bangunan :</label>
                              <s:text name="hakmilik.noBangunan"/>
                          </p>
                          <p><label>No Tingkat :</label>
                              <s:text name="hakmilik.noTingkat"/>
                          </p>
                          <p><label>No Petak :</label>
                              <s:text name="hakmilik.noPetak"/>
                          </p>
                    </c:if>
                    <%-- <p><label>Kod Lot / No Lot : </label>
                         <s:select name="hakmilik.lot.kod" id="lot" value="${actionBean.hakmilik.lot.kod}">
                             <s:option value="">Sila Pilih</s:option>
                             <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod" />
                         </s:select> /
                         <s:text name="hakmilik.noLot" id="noLot" value="${actionBean.hakmilik.noLot}" onblur="checkPelan(this.value);"/>
                     </p>--%>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="Step2" value="Kembali" class="btn" />
                        <s:submit name="Cancel" value="Batal" class="btn" />
                        <s:submit name="Step4" value="Seterusnya" class="btn" id="Step4" />
                        <%--<s:button name="janaIDHakmilik" value="Jana" id="janaBtn" class="btn" onclick="save(this.name,this.form);"/>--%>
                        <%--<s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>--%>
                    </p>
                </fieldset>
            </s:form>
        </div>

    </body>
</html>
