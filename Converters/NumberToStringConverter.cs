using System.Globalization;
using System.Text.Json;
using System.Text.Json.Serialization;

namespace nocturnal.Converters;

public class NumberToStringConverter : JsonConverter<string>
{
    public override string? Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
    {
        return reader.TokenType == JsonTokenType.Number
            ? reader.GetDouble().ToString(CultureInfo.CurrentCulture)
            : reader.GetString();
    }

    public override void Write(Utf8JsonWriter writer, string value, JsonSerializerOptions options)
    {
        writer.WriteStringValue(value);
    }
}