<%--
    Document   : nota_daftar
    Created on : Nov 30, 2009, 10:28:10 AM
    Author     : mohd.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>

<script type="text/javascript">

    $(document).ready(function() {
        var kod = "${actionBean.permohonan.kodUrusan.kod}";
         if(kod == "DEV")
        {   $('#DEV').show();
            $('#HLTE').hide();
            $('#LTS').hide();
    <%--$('#MAJD').hide();--%>
                $('#MAJB').hide();
                $('#HASIL').hide();}

            else if (kod == "HLTE")
            {$('#DEV').hide();
                $('#HLTE').show();
                $('#LTS').hide();
    <%--$('#MAJD').hide();--%>
                $('#MAJB').hide();
                $('#HASIL').hide();}
            else if (kod == "HLTEB")
            {$('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').hide();
    <%--$('#MAJD').hide();--%>
                $('#MAJB').hide();
                $('#HASIL').hide();}
            else if (kod == "LTS")
            {$('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').show();
    <%--$('#MAJD').hide();--%>
                $('#MAJB').hide();
                $('#HASIL').hide();}
            else if (kod== "MAJB" || value == "MAJD")
            {$('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').hide();
    <%--$('#MAJD').show();--%>
                $('#MAJB').show();
                $('#HASIL').hide();}
            else if (kod == "HASIL")
            {$('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').hide();
    <%--$('#MAJD').show();--%>
                $('#MAJB').hide();
                $('#HASIL').show();}
            else
            {
                $('#DEV').show();
                $('#HLTE').show();
                $('#LTS').show();
                $('#MAJB').show();
                $('#MAJD').show();
                $('#HASIL').show();

            }

              <%--  $('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').hide();
                $('#MAJB').hide();
                $('#MAJD').hide();
                $('#HASIL').hide();--%>



        
    });

    function kenalUrusan(value)
    {
    <%--var kod = "${actionBean.permohonan.kodUrusan.kod}";--%>
        if(value == "DEV")
        {   $('#DEV').show();
            $('#HLTE').hide();
            $('#LTS').hide();
    <%--$('#MAJD').hide();--%>
                $('#MAJB').hide();
                $('#HASIL').hide();}

            else if (value == "HLTE")
            {$('#DEV').hide();
                $('#HLTE').show();
                $('#LTS').hide();
    <%--$('#MAJD').hide();--%>
                $('#MAJB').hide();
                $('#HASIL').hide();}
            else if (value == "LTS")
            {$('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').show();
    <%--$('#MAJD').hide();--%>
                $('#MAJB').hide();
                $('#HASIL').hide();}
            else if (value == "MAJB" || value == "MAJD")
            {$('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').hide();
    <%--$('#MAJD').show();--%>
                $('#MAJB').show();
                $('#HASIL').hide();}
            else if (value == "HASIL")
            {$('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').hide();
    <%--$('#MAJD').show();--%>
                $('#MAJB').hide();
                $('#HASIL').show();}
            else if (value == "LAIN-LAIN")
            {
                $('#DEV').show();
                $('#HLTE').show();
                $('#LTS').show();
                $('#MAJB').show();
                $('#MAJD').show();
                $('#HASIL').show();

            }
            else
            {
                $('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').hide();
                $('#MAJB').hide();
                $('#MAJD').hide();
                $('#HASIL').hide();

            }
        

    }

    function changeTujuan(value){

        if(value == "DEV")
        {   $('#DEV').show();
            $('#HLTE').hide();
            $('#LTS').hide();
    <%--$('#MAJD').hide();--%>
                $('#MAJB').hide();
                $('#HASIL').hide();}

            else if (value == "HLTE")
            {$('#DEV').hide();
                $('#HLTE').show();
                $('#LTS').hide();
    <%--$('#MAJD').hide();--%>
                $('#MAJB').hide();
                $('#HASIL').hide();}
            else if (value == "LTS")
            {$('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').show();
    <%--$('#MAJD').hide();--%>
                $('#MAJB').hide();
                $('#HASIL').hide();}
            else if (value == "MAJB" || value == "MAJD")
            {$('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').hide();
    <%--$('#MAJD').show();--%>
                $('#MAJB').show();
                $('#HASIL').hide();}
            else if (value == "HASIL")
            {$('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').hide();
    <%--$('#MAJD').show();--%>
                $('#MAJB').hide();
                $('#HASIL').show();}
            else if (value == "LAIN-LAIN")
            {
                $('#DEV').show();
                $('#HLTE').show();
                $('#LTS').show();
                $('#MAJB').show();
                $('#MAJD').show();
                $('#HASIL').show();

            }
            else
            {
                $('#DEV').hide();
                $('#HLTE').hide();
                $('#LTS').hide();
                $('#MAJB').hide();
                $('#MAJD').hide();
                $('#HASIL').hide();

            }
        
        }

    
    

</script>


    
<form:form beanclass="etanah.view.stripes.NotaDaftarActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Nota
            </legend>
            <p style="color:red">
                *Isi Yang Berkenaan Sahaja.
            </p>
            <p>
                <label for="noSidang">Unit :</label>
                <s:select name="select" onchange="javaScript:changeTujuan(this.options[selectedIndex].text)" style="width:13%;">
                    <s:option value="" >Sila Pilih</s:option>
                    <s:option value="DEV">DEV</s:option>
                    <s:option value="HLTE">HLTE</s:option>
                    <s:option value="LTS">LTS</s:option>
                    <s:option value="MAJB">MAJB</s:option>
                    <s:option value="MAJD">MAJD</s:option>
                    <s:option value="HASIL">HASIL</s:option>
                    <s:option value="LAIN-LAIN">LAIN-LAIN</s:option>
                </s:select>

            </p>
            <p>
                <label for="noRujukan">Nombor Fail / ID Permohonan :</label>
                <s:text name="noRujukan" size="24"></s:text>
            </p>
            <p>
                <label for="noSidang">Nombor Kertas:</label>
                <s:select name="kodMesy" id="kodMesy" style="width:6%;" >
                    <s:option value="" >Sila Pilih</s:option>
                    <s:option value="PTD">PTD</s:option>
                    <s:option value="MMK">MMK</s:option>
                    <s:option value="MB">MB</s:option>
                </s:select>

                <s:text name="noMesy" size="10"></s:text>
                
            </p>

            <p>
                <label for="trhSidang">Tarikh Mesyuarat :</label>
                <s:text name="trhSidang" class="datepicker" size="24"/>
            </p>
            <p>
                <label for="noRujukan">Nombor Warta :</label>
                <s:text name="noRujukan" size="24"></s:text>
            </p>
            <p>
                <label for="trhRujukan">Tarikh Warta :</label>
                <s:text name="trhRujukan" id="tkhWartadatepicker" class="datepicker" size="24"/>
            </p>
            <div id="DEV">
                <p>
                    <label for="noRujukan">Jenis Perintah :</label>
                    <s:select name="kodMesy" id="kodMesy" style="width:13%;" >
                        <s:option value="" >Sila Pilih</s:option>
                        <s:option value="PTD">PD</s:option>
                        <s:option value="MMK">MK</s:option>
                    </s:select>
                </p>
                <p>
                    <label for="noRujukan">Nombor Perintah :</label>
                    <s:text name="noRujukan" size="24"></s:text>
                </p>
                <p>
                    <label for="noRujukan">Sekatan Kepentingan :</label>
                    <s:text name="noRujukan" size="24"></s:text>
                </p>
            </div>
            <div id="HASIL">
                <p id="HASIL">
                    <label for="noRujukan">Jenis Notis :</label>
                    <s:select name="kodMesy" id="kodMesy" style="width:13%;" >
                        <s:option value="" >Sila Pilih</s:option>
                        <s:option value="PTD">Notis 6A</s:option>
                        <s:option value="MMK">Notis 7A</s:option>
                        <s:option value="MMK">Notis 8A</s:option>
                    </s:select>
                </p>
                <p id="HASIL">
                    <label for="trhRujukan">Tarikh Notis :</label>
                    <s:text name="trhRujukan" id="tkhWartadatepicker" class="datepicker" size="24"/>
                </p>
                <p id="HASIL">
                    <label for="noRujukan">Pindaan Cukai :</label>
                    <s:text name="noRujukan" size="24"></s:text>
                </p>
            </div>
            <p id="HLTE">
                <label for="noRujukan">Keluasan :</label>
                <s:text name="noRujukan" size="24"></s:text>
            </p>
            <p id="LTS">
                <label for="noRujukan">Tempoh :</label>
                <s:text name="noRujukan" size="24"></s:text>
            </p>
            <p id="MAJB">
                <label for="noRujukan">Majlis Perbandaran :</label>
                <s:text name="noRujukan" size="24"></s:text>
            </p>
            <br/>


        </fieldset>

    </div>
    <br>
    <table style="margin-left: auto; margin-right: auto;">
        <tr>
            <td>&nbsp;</td>
            <td><div >

                    <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="save" value="Simpan"/>


                </div>
            </td>
        </tr>
    </table>

    <br>

</form:form>

   