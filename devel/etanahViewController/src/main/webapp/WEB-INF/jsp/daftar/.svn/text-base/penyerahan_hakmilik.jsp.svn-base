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
        <c:set value="${actionBean.mohon.kodUrusan.kod}" var="kodUrusan"/>
        <%
                    Pengguna p = (Pengguna) request.getSession().getAttribute("_KEY_USER");
                    //String kodDaerah = p.getKodCawangan().getDaerah().getKod();
%>
        <script type="text/javascript">
                    
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

            function checkPelan(f){
                //alert(f);
                var noLot = f
                var kodDaerah = $('#kodDaerah').val();
                var kodBPM = $('#kodBPM').val();
                var kodNegeri = $('#kodNegeri').val();
                //alert(kodDaerah);
                //var q = $(e).formSerialize();
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkPelan&noLot='+noLot+'&kodDaerah='+kodDaerah+'&kodNegeri='+kodNegeri+'&kodBPM='+kodBPM,
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
                //$("#messages").html('<b>Sila masukkan maklumat berkaitan untuk menjana hakmilik baru</b>');
                //$("#messages").show();
                maximizeWindow();
                $("#lot").val('1');               
                //$("#namaNegeri").val().toUpperCase();
                filterKodBPM($('#kodDaerah').val());

                $("#namaDaerah").val($("#kodDaerah").val());
         
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
                $("#kodJenisHakmilik").blur( function() {
                    var valueJenisHakmilik = $("#kodJenisHakmilik").val();
                    $("#namaJenisHakmilik").val(valueJenisHakmilik);
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
            <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
            <s:form beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean" id="kemasukanPerincianHakmilik">
                <s:hidden name="idHakmilik" />
                <fieldset class="aras1">
                    <legend>
                        Penyerahan Hakmilik
                    </legend>
                    <p>
                        <label>No Perserahan :</label>
                        ${actionBean.mohon.idPermohonan}
                        &nbsp
                    </p>
                    <p>
                        <label>Urusan :</label>
                        ${actionBean.mohon.kodUrusan.kod} - ${actionBean.mohon.kodUrusan.nama}
                        &nbsp
                    </p>


                    <%--<c:if test="${actionBean.p.kodUrusan.kod ne 'HSBM' && actionBean.p.kodUrusan.kod ne 'HKBM'
                                  && actionBean.p.kodUrusan.kod ne 'HKPB' && actionBean.p.kodUrusan.kod ne 'HSPB' }">--%>
                    <c:if test="${actionBean.mohon.kodUrusan.kod ne 'HSBM' && actionBean.mohon.kodUrusan.kod ne 'HKBM'
                                  && actionBean.mohon.kodUrusan.kod ne 'HSC' && actionBean.mohon.kodUrusan.kod ne 'HKC'
                                  && actionBean.mohon.kodUrusan.kod ne 'HSCTK' && actionBean.mohon.kodUrusan.kod ne 'HKCTK'}">
                        <%--<c:if test="${actionBean.mohon.kodUrusan.kod ne 'HSBM' || actionBean.mohon.kodUrusan.kod ne 'HKBM'}">--%>
                        <p>
                            <label>Hakmilik Lama :</label>

                            <%--<s:text name="idHakmilikAsal" id="idHakmilikAsal"/>--%>
                            <s:select name="idHakmilikAsal" id="idHakmilikAsal">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.listMohonHakmilikSebelum}" value="hakmilik.idHakmilik" label="hakmilik.idHakmilik"/>
                            </s:select>

                        </p>
                    </c:if>
                    <p>
                        <label>Hakmilik Yang Dikeluarkan :</label>
                        <s:text name="totalHakmilik" id="totalHakmilik" size="4"/>
                    </p>
                    <%--TODO: GET NAMA & KOD NEGERI FROM SESSION--%>
                    <p>
                        <label>Negeri</label>
                        <s:text name="kodNegeri" id="kodNegeri" readonly="true" size="4"/> - <s:text name="namaNegeri" id="namaNegeri" readonly="true" style="text-transform: uppercase;"/>
                    </p>
                    <p>
                        <label>Daerah</label>
                        <s:text name="kodDaerah" size="4" id="kodDaerah" readonly="true" />
                        -
                        <s:select name="hakmilik.daerah.kod" id="namaDaerah" onchange="filterKodBPM(this.value);">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodDaerah}" label="nama" value="kod"/>
                        </s:select>


                    </p>
                    <div id="partialKodBPM">

                    </div>
                    <p>
                        <label>Jenis Hakmilik</label>
                        <s:text name="kodJenisHakmilik" size="4" id="kodJenisHakmilik"/> -
                        <s:select name="hakmilik.kodHakmilik.kod" id="namaJenisHakmilik">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodHakmilik}" label="nama" value="kod" />
                        </s:select>

                    </p>
                    <%-- <p><label>Kod Lot / No Lot : </label>
                         <s:select name="hakmilik.lot.kod" id="lot" value="${actionBean.hakmilik.lot.kod}">
                             <s:option value="">Sila Pilih</s:option>
                             <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod" />
                         </s:select> /
                         <s:text name="hakmilik.noLot" id="noLot" value="${actionBean.hakmilik.noLot}" onblur="checkPelan(this.value);"/>
                     </p>--%>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="janaIDHakmilik" value="Jana" id="janaBtn" class="btn" onclick="save(this.name,this.form);"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
                    </p>
                </fieldset>

            </div>
        </s:form>
    </body>
</html>
